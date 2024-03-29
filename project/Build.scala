import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._

import com.typesafe.sbt.SbtAtmos.{ Atmos, atmosSettings }


object Build extends Build {

  lazy val simpleakka = Project(
    "simpleakka",
    file("."),
    settings = commonSettings ++ Seq(
      organization := "com.jtm.training",
      version := "2.0.0",
      scalaVersion := Version.scala,
      scalacOptions ++= Seq(
        "-unchecked",
        "-deprecation",
        "-Xlint",
        "-language:_",
        "-target:jvm-1.6",
        "-encoding", "UTF-8"
      ),
      libraryDependencies ++= Seq(
        Dependency.Compile.akkaActor,
        Dependency.Compile.akkaSlf4j,
        Dependency.Compile.logbackClassic,
        Dependency.Test.akkaTestkit,
        Dependency.Test.scalaTest
      ),
      retrieveManaged := true,
      initialCommands in console := "import com.jtm.training.simpleakka._; import akka.actor._",
      initialCommands in (Test, console) <<= (initialCommands in console)(_ + "; import akka.testkit._")
    )
  )
    .configs(Atmos)
    .settings(atmosSettings: _*)

  def commonSettings =
    Defaults.defaultSettings ++ 
    scalariformSettings

  object Version {
    val scala = "2.10.1"
    val akka = "2.1.2"
  }

  object Dependency {

    object Compile {
      val akkaActor = "com.typesafe.akka" %% "akka-actor" % Version.akka
      val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % Version.akka
      val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.0.9"
    }

    object Test {
      val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % Version.akka % "test"
      val scalaTest = "org.scalatest" %% "scalatest" % "2.0.M6-SNAP12" % "test"
    }
  }
}
