package finatra.beetl

import java.util.concurrent.ConcurrentHashMap

import finatra.views.beetl.Beetl

import scala.collection.JavaConverters._

private[beetl] class BeetlTemplateNameLookup {
  private val classToTemplateNameCache = new ConcurrentHashMap[Class[_], String]().asScala
  private val suffix = if (System.getProperty("beetl.template.suffix") != null) System.getProperty("beetl.template.suffix") else ".html"

  /* Public */

  def getTemplateName(obj: Any): String = {
    obj match {
      case fbc: BeetlBodyComponent if !fbc.template.isEmpty => fbc.template
      case fbc: BeetlBodyComponent => lookupViaAnnotation(fbc.data)
      case _ => lookupViaAnnotation(obj)
    }
  }

  /* Private */

  private def lookupViaAnnotation(obj: Any): String = {
    classToTemplateNameCache.getOrElseUpdate(obj.getClass, {
      val viewAnnotation = obj.getClass.getAnnotation(classOf[Beetl])
      viewAnnotation.value + suffix
    })
  }
}
