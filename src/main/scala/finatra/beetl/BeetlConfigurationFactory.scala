package finatra.beetl

import java.io.File

import org.beetl.core.resource.{ClasspathResourceLoader, FileResourceLoader}
import org.beetl.core.{Configuration, GroupTemplate}
import java.nio.charset.StandardCharsets

case class BeetlConfigurationFactory(groupTemplate: GroupTemplate)

object BeetlConfigurationFactory {
  def apply(templateDir: String): BeetlConfigurationFactory = {
    
    BeetlConfigurationFactory(new GroupTemplate(new ClasspathResourceLoader(templateDir + "/", StandardCharsets.UTF_8.displayName()), Configuration.defaultConfiguration))
  }

  def apply(templateDir: File): BeetlConfigurationFactory = {
    BeetlConfigurationFactory(new GroupTemplate(new FileResourceLoader(templateDir.getAbsolutePath, StandardCharsets.UTF_8.displayName()), Configuration.defaultConfiguration))
  }
}