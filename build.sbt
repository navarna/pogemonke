name := "pogomonke"

organization := "groupe5"

version := "1.0"

scalaVersion := "2.11.6"

lazy val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-swing" % "2.11.0-M7",
  "com.madgag" % "bfg" % "1.11.1",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


fork in run := true