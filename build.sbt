lazy val akkaHttpVersion = "10.1.5"
lazy val akkaVersion = "2.5.18"
lazy val circeVersion = "0.10.0"

lazy val labor = (project in file(".")).settings(
  inThisBuild(List(organization := "com.deliganli", scalaVersion := "2.12.7")),
  name := "labor",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  ),
  libraryDependencies ++= Seq("joda-time" % "joda-time" % "2.10.1"),
  libraryDependencies ++= Seq("io.circe" %% "circe-core", "io.circe" %% "circe-generic", "io.circe" %% "circe-parser")
    .map(_ % circeVersion),
  libraryDependencies += "de.heikoseeberger" %% "akka-http-circe" % "1.22.0"
)

addCompilerPlugin(("org.scalamacros" % "paradise" % "2.1.0").cross(CrossVersion.full))
