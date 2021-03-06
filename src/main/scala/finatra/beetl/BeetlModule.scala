package finatra.beetl

import java.io.File
import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.finatra.http.internal.marshalling.MessageBodyManager
import com.twitter.finatra.modules.FileResolverModule
import com.twitter.inject.annotations.Flag
import com.twitter.inject.{Injector, TwitterModule}
import finatra.views.beetl.Beetl

object BeetlModule extends TwitterModule {
  private val templatesDir = flag("beetl.templates.dir", "templates", "templates resource directory")

  override def modules = Seq(FileResolverModule)

  @Provides
  @Singleton
  def provideBeetlFactory(@Flag("local.doc.root") localDocRoot: String): BeetlConfigurationFactory = {
    localDocRoot match {
      case s: String if s != null && s.trim.length > 0 => BeetlConfigurationFactory(new File(localDocRoot, templatesDir()))
      case _ => BeetlConfigurationFactory(templatesDir())
    }
  }

  override def singletonStartup(injector: Injector) {
    val manager = injector.instance[MessageBodyManager]
    manager.addByAnnotation[Beetl, BeetlMessageBodyWriter]()
    manager.addByComponentType[BeetlBodyComponent, BeetlMessageBodyWriter]()
  }
}
