name := "akka-var"

version := "1.0"

scalaVersion := "2.11.7"

lazy val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion

)

// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"

// https://mvnrepository.com/artifact/uk.com.robust-it/cloning
libraryDependencies += "uk.com.robust-it" % "cloning" % "1.9.2"



testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
