name := "blank"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq (
        "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
    )

libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.1.2"
  )

libraryDependencies ++= Seq(
      "net.databinder" %% "dispatch" % "0.8.10"
  )

libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-actors" % "2.10.2"
  )

libraryDependencies ++= Seq(
      "net.databinder" %% "dispatch-core" % "0.8.10"
  )

libraryDependencies ++= Seq(
      "net.databinder" %% "dispatch-futures" % "0.8.10"
  )

libraryDependencies ++= Seq(
      "net.databinder" %% "dispatch-nio" % "0.8.10"
  )

libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.6.4",
        "org.slf4j" % "slf4j-simple" % "1.6.4"
    )

libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-core" % "3.2.11",
        "org.json4s" %% "json4s-native" % "3.2.11",
          "org.json4s" %% "json4s-jackson" % "3.2.11",
            "net.databinder" %% "unfiltered-netty" % "0.8.0" % "test",
              "net.databinder.dispatch" % "dispatch-json4s-native_2.11" % "0.11.1"
          )

libraryDependencies ++= Seq(
      "io.argonaut" %% "argonaut" % "6.0.4"  
  )

initialCommands := "import dispatch._"
