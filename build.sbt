name := """bitspoke-www"""

organization := "bitspoke"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

scalacOptions += "-target:jvm-1.7"

libraryDependencies ++= Seq(
  "org.mongodb" % "casbah_2.11" % "2.7.2",
  "org.scaldi" % "scaldi-play_2.11" % "0.3.3"
)
