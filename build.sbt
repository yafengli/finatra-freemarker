import Build._

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

lazy val root = (project in file(".")).settings(
  organization := "greatbit",
  name := "finatra-freemarker",
  version := $("finatra"),
  scalaVersion := $("scala"),
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),
  libraryDependencies ++= Seq(
    "com.twitter" %% "finatra-http" % $("finatra"),
    "org.antlr" % "antlr4" % $("antlr4") exclude("org.antlr", "antlr-runtime"),
    "com.ibeetl" % "beetl" % $("beetl"),
    "org.freemarker" % "freemarker" % $("freemarker"),
    "commons-beanutils" % "commons-beanutils" % $("commons-beanutils"),
    "ch.qos.logback" % "logback-classic" % $("logback"),
    "com.twitter" %% "finatra-http" % $("finatra") % "test" classifier "tests",
    "org.scalatest" %% "scalatest" % $("scalatest") % "test")
)
