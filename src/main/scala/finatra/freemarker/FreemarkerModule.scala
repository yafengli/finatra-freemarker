package finatra.freemarker

import java.io.File
import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.finatra.http.internal.marshalling.MessageBodyManager
import com.twitter.finatra.modules.FileResolverModule
import com.twitter.inject.annotations.Flag
import com.twitter.inject.{Injector, TwitterModule}
import finatra.views.freemarker.Freemarker

object FreemarkerModule extends TwitterModule {
  private val templatesDir = flag("freemarker.templates.dir", "templates", "templates resource directory")

  override def modules = Seq(FileResolverModule)

  @Provides
  @Singleton
  def provideFreemarkerFactory(@Flag("local.doc.root") localDocRoot: String): FreemarkerConfigurationFactory = {
    localDocRoot match {
      case s: String if s != null && s.trim.length > 0 => FreemarkerConfigurationFactory((new File(new File(localDocRoot), templatesDir())))
      case _ => FreemarkerConfigurationFactory(Thread.currentThread().getContextClassLoader, templatesDir())
    }
  }

  override def singletonStartup(injector: Injector) {
    val manager = injector.instance[MessageBodyManager]
    manager.addByAnnotation[Freemarker, FreemarkerMessageBodyWriter]()
    manager.addByComponentType[FreemarkerBodyComponent, FreemarkerMessageBodyWriter]()
  }
}
