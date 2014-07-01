name := """bitspoke-www"""

organization := "bitspoke"

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

scalacOptions += "-target:jvm-1.7"

libraryDependencies ++= Seq(
  "org.webjars" % "html5shiv" % "3.7.0",
  "org.webjars" % "respond" % "1.4.2",
  "org.webjars" % "bootstrap" % "3.1.1-1",
  "org.webjars" % "angularjs" % "1.2.17",
  "org.webjars" % "font-awesome" % "4.1.0",
  "com.github.fakemongo" % "fongo" % "1.5.1" % "test"
)
