name := "stream-collections"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.10.5", "2.11.6")

organization := "com.gonitro"

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
    else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

homepage := Some(url("https://github.com/Nitro/streamcollections"))

resolvers ++= Seq("Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-iteratees" % "2.3.8",
  "org.joda" % "joda-convert" % "1.6",
  "org.specs2" %% "specs2-core" % "3.0.1" % "test"
)

scalacOptions ++= Seq(
  "-optimize"
)

scalacOptions in Test ++= Seq(
  "-Yrangepos",
  "-feature"
)

scalariformSettings
