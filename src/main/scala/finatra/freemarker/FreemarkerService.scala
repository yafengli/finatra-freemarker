package finatra.freemarker

import java.io.{ByteArrayOutputStream, OutputStreamWriter, StringWriter}
import java.nio.charset.StandardCharsets
import javax.inject.{Inject, Singleton}

import com.twitter.io.Buf

@Singleton
class FreemarkerService @Inject()(factory: FreemarkerConfigurationFactory) {

  private[freemarker] def createBuffer(templateName: String, obj: Any): Buf = {
    val outputStream = new ByteArrayOutputStream(1024)
    val writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
    try {
      val template = factory.configuration.getTemplate(templateName)
      template.process(obj, writer)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        println(s"1-obj:${obj.getClass.toString}")
        println(s"${obj}")
    }
    finally {
      writer.close()
    }

    Buf.ByteArray.Owned(outputStream.toByteArray)
  }

  def createString(templateName: String, obj: Any): String = {
    val writer = new StringWriter()
    try {
      val template = factory.configuration.getTemplate(templateName)
      template.process(obj, writer)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        println(s"2-obj:${obj.getClass.toString}")
        println(s"${obj}")
    }

    writer.toString
  }
}
