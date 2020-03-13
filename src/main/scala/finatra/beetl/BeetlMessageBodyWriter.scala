package finatra.beetl

import java.io.{ByteArrayOutputStream, OutputStreamWriter, StringWriter}
import java.nio.charset.StandardCharsets
import java.util
import javax.inject.{Inject, Singleton}

import com.google.common.net.MediaType
import com.twitter.finatra.http.marshalling.{MessageBodyWriter, WriterResponse}
import com.twitter.io.Buf

import scala.collection.JavaConverters._

@Singleton
class BeetlMessageBodyWriter @Inject() (factory: BeetlConfigurationFactory, templateNameLookup: BeetlTemplateNameLookup) extends MessageBodyWriter[Any] {

  private[beetl] def createBuffer(obj: Any): Buf = {
    val outputStream = new ByteArrayOutputStream(1024)
    val writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
    try {
      val template = factory.groupTemplate.getTemplate(templateNameLookup.getTemplateName(obj))
      template.binding(transToJavaMap(obj))
      template.renderTo(writer)
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      writer.close()
    }

    Buf.ByteArray.Owned(outputStream.toByteArray)
  }

  def createString(obj: Any): String = {
    val writer = new StringWriter()
    try {
      val template = factory.groupTemplate.getTemplate(templateNameLookup.getTemplateName(obj))
      template.binding(transToJavaMap(obj))
      template.renderTo(writer)
    } catch {
      case e: Exception => e.printStackTrace()
    }

    writer.toString
  }

  override def write(obj: Any): WriterResponse = {
    WriterResponse(MediaType.HTML_UTF_8.toString(), createBuffer(obj))
  }

  /* Private */
  private def transToJavaMap(obj: Any): util.Map[String, Any] = {
    (Map[String, Any]() /: obj.getClass.getDeclaredFields) { (a, f) =>
      f.setAccessible(true)
      a + (f.getName -> f.get(obj))
    } asJava
  }
}
