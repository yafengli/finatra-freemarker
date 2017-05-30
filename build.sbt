import Build._

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

lazy val root = (project in file(".")).settings(
  organization := "greatbit",
  name := "finatra-freemarker",
  version := $("finatra"),
  scalaVersion := $("scala"),
  crossScalaVersions := Seq("2.11.8", $("scala")),
  libraryDependencies ++= Seq(
    "com.twitter" %% "finatra-http" % $("finatra"),
    "org.freemarker" % "freemarker" % $("freemarker"),
    "commons-beanutils" % "commons-beanutils" % $("commons-beanutils"),
    "ch.qos.logback" % "logback-classic" % $("logback"),
    "com.twitter" %% "finatra-http" % $("finatra") % "test" classifier "tests",
    "org.scalatest" %% "scalatest" % $("scalatest") % "test")
)
