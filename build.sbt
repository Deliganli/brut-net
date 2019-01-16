lazy val brutnet = (project in file(".")).settings(
  name := "brut-net",
  organization := "com.deliganli",
  scalaVersion := "2.12.7",
  version := "0.0.1",
  libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.1" % Test)
)
