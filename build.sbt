ThisBuild / version      := "1.0.0"
ThisBuild / scalaVersion := "2.12.6"
ThisBuild / organization := "org.learn"

lazy val main = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "Main",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )

