import Build._

lazy val root = (project in file(".")).settings(
  inThisBuild(List(
    organization := "com.twitter",
    name := "finatra-freemarker",
    version := $("finatra"),
    crossScalaVersions := Seq("2.11.8", "2.12.1")
  )),
  name := "hello",
  mainClass := Some("com.example.HelloServerMain"),
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
