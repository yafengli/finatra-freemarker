import Build._

lazy val root = (project in file(".")).settings(
  organization := "com.twitter",  
  name := "finatra-freemarker",  
  version := $("finatra"),
  scalaVersion := "2.12.1",
  crossScalaVersions := Seq("2.11.8", "2.12.1"),
  libraryDependencies ++= Seq(
    "com.twitter" %% "finatra-http" % $("finatra"),
    "com.twitter" %% "finatra-httpclient" % $("finatra"),
    "com.twitter" %% "finatra-slf4j" % $("finatra"),
    "com.twitter" %% "finatra-thrift" % $("finatra"),
    "com.twitter" %% "inject-server" % $("finatra"),
    "com.twitter" %% "inject-app" % $("finatra"),
    "org.freemarker" % "freemarker" % $("freemarker"),
    "commons-beanutils" % "commons-beanutils" % $("commons-beanutils"),
    "ch.qos.logback" % "logback-classic" % $("logback"),
    "com.twitter" %% "finatra-http" % $("finatra") % "test" classifier "tests",
    "com.twitter" %% "inject-server" % $("finatra") % "test" classifier "tests",
    "com.twitter" %% "inject-app" % $("finatra") % "test" classifier "tests",
    "com.twitter" %% "inject-core" % $("finatra") % "test" classifier "tests",
    "com.twitter" %% "inject-modules" % $("finatra") % "test" classifier "tests",
    "org.mockito" % "mockito-core" % $("mockito") % "test",
    "org.scalatest" %% "scalatest" % $("scalatest") % "test")
)
