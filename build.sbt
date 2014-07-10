name := """bitspoke-www"""

organization := "bitspoke"

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

scalacOptions += "-target:jvm-1.7"

libraryDependencies ++= Seq(
  "org.mongodb" % "casbah_2.11" % "2.7.2",
  "com.google.inject" % "guice" % "3.0",
  "com.tzavellas" % "sse-guice" % "0.7.1",
  "org.webjars" % "html5shiv" % "3.7.0",
  "org.webjars" % "respond" % "1.4.2",
  "org.webjars" % "bootstrap" % "3.1.1-1",
  "org.webjars" % "angularjs" % "1.2.17",
  "org.webjars" % "font-awesome" % "4.1.0",
  "org.webjars" % "marked" % "0.3.2-1",
  "org.webjars" % "cryptojs" % "3.1.2",
  "com.github.fakemongo" % "fongo" % "1.5.1" % "test"
)


// JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

lazy val testJs = taskKey[Int]("Test Javascripts")

testJs in Test := {
    "npm run test-single-run" !
}
/*
test := Def.taskDyn {
  (testJs in Test).value match {
    case 0 => Def.task {
      (test in Test).value
    }
    case _ => Def.task()
  }
}.value
*/