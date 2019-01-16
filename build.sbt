lazy val brutnet = (project in file(".")).settings(
  name := "brut-net",
  organization := "com.deliganli",
  scalaVersion := "2.12.7",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % Test,
    "joda-time"     % "joda-time"  % "2.10.1"
  )
)
