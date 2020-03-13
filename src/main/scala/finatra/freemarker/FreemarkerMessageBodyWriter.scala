package finatra.freemarker

import java.io.{ByteArrayOutputStream, OutputStreamWriter, StringWriter}
import java.nio.charset.StandardCharsets
import javax.inject.{Inject, Singleton}

import com.google.common.net.MediaType
import com.twitter.finatra.http.marshalling.{MessageBodyWriter, WriterResponse}
import com.twitter.io.Buf

@Singleton
class FreemarkerMessageBodyWriter @Inject()(factory: FreemarkerConfigurationFactory,
                                            templateLookup: FreemarkerTemplateNameLookup)
  extends MessageBodyWriter[Any] {

  def createBuffer(obj: Any): Buf = {
    val outputStream = new ByteArrayOutputStream(1024)
    val writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
    try {
      val template = factory.configuration.getTemplate(templateLookup.getTemplateName(obj))
      template.process(transToMap(obj), writer)
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      writer.close()
    }

    Buf.ByteArray.Owned(outputStream.toByteArray)
  }

  def createString(obj: Any): String = {
    val writer = new StringWriter()
    try {
      val template = factory.configuration.getTemplate(templateLookup.getTemplateName(obj))
      template.process(transToMap(obj), writer)
    } catch {
      case e: Exception => e.printStackTrace()
    }

    writer.toString
  }

  override def write(obj: Any): WriterResponse = {
    WriterResponse(
      MediaType.HTML_UTF_8.toString(),
      createBuffer(obj))
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
