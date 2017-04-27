package finatra.freemarker

import javax.inject.{Inject, Singleton}

import com.google.common.net.MediaType
import com.twitter.finatra.http.marshalling.{MessageBodyWriter, WriterResponse}

@Singleton
class FreemarkerMessageBodyWriter @Inject()(freemarkerService: FreemarkerService,
                                            templateLookup: FreemarkerTemplateNameLookup)
  extends MessageBodyWriter[Any] {
  override def write(obj: Any): WriterResponse = {
    WriterResponse(
      MediaType.HTML_UTF_8,
      freemarkerService.createBuffer(
        templateLookup.getTemplateName(obj),
        transToMap(obj)))
  }

  /**
    * Private
    *
    * @param obj
    * @return Map[String,Any] must ScalaObjectMapper
    */
  private def transToMap(obj: Any): Map[String, Any] = {
    (Map[String, Any]() /: obj.getClass.getDeclaredFields) { (a, f) =>
      f.setAccessible(true)
      a + (f.getName -> f.get(obj))
    }
  }
}
