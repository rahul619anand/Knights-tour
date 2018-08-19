ThisBuild / version      := "1.0.0"
ThisBuild / scalaVersion := "2.12.6"
ThisBuild / organization := "org.solve.pawnstour"

lazy val main = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "PawnsTour",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )

