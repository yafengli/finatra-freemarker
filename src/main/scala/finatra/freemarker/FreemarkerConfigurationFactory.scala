package finatra.freemarker

import java.io.File
import java.nio.charset.StandardCharsets._

import freemarker.template.{Configuration, TemplateExceptionHandler}

case class FreemarkerConfigurationFactory(configuration: Configuration)

object FreemarkerConfigurationFactory {
  val FILE_ENCODING = "file.encoding"

  def apply(classLoader: ClassLoader, templateDir: String): FreemarkerConfigurationFactory = {
    val conf = configuration()
    conf.setClassLoaderForTemplateLoading(classLoader, templateDir)
    FreemarkerConfigurationFactory(conf)
  }

  def apply(templateDir: File): FreemarkerConfigurationFactory = {
    val conf = configuration()
    conf.setDirectoryForTemplateLoading(templateDir)
    FreemarkerConfigurationFactory(conf)
  }

  private def configuration(): Configuration = {
    val configuration = new Configuration(Configuration.VERSION_2_3_26)
    configuration.setDefaultEncoding(sys.props.get(FILE_ENCODING).getOrElse(UTF_8.name()))
    configuration.setObjectWrapper(new ScalaObjectWrapper)
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER)
    configuration.setLogTemplateExceptions(false)
    configuration
  }
}