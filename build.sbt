name := "s3cryptview"

organization := "org.decaf"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-s3" % "1.9.35",
  "org.scala-lang.modules" %% "scala-swing" % "2.0.0-SNAPSHOT"
)

// tests
resolvers ++= Seq(
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.5" % "test"
)
