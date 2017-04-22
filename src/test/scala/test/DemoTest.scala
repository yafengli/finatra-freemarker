package test

import java.io.StringWriter

import finatra.freemarker.FreemarkerConfigurationFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.beans.BeanProperty
import scala.sys.process._

class DemoTest extends FlatSpec with Matchers {
  lazy val conf = FreemarkerConfigurationFactory(Thread.currentThread.getContextClassLoader, "").configuration
  "A Stack" should "pop values in last-in-first-out order" in {
    val r = """^$""".r
    println("Hello, world!")
    r.findFirstMatchIn("123123")

    if (System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) {
      val lines = "vmstat" #|| "nothing" lineStream

      for (l <- lines) {
        val ss = l.trim().split(" +")
        ss.foreach { c => print(s"${c} ") }
        println(s"${ss.length}:${ss.mkString("\\t")}")
      }
      view(lines.last.trim.split(" +"))
    }
  }

  "A freemarker template" should "" in {
    val root = Map("name" -> "yafengli@sina.com", "users" -> List(User("1", 1), User("2", 2)))
    println(template("/test/hello", root))
  }

  def view(ss: Array[String]): Unit = {

    if (ss.length >= 17) {
      println(s"${ss(11)} ${ss(12)} = ${ss(11).toInt + ss(12).toInt}")
    }
  }

  def template(name: String, root: Any): String = {
    val result = new StringWriter
    conf.getTemplate(name + ".ftl").process(root, result)
    result.toString
  }
}

case class User(@BeanProperty name: String, @BeanProperty age: Int)