ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "leap-year-function-using-function-applicative"
  )

libraryDependencies ++= Seq(
  "org.typelevel"   %% "cats-core"      % "2.12.0",
)
