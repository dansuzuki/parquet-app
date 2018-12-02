lazy val root = (project in file("."))
  .settings(
    organization := "me.dan",
    name := "just-parquet-app",
    version := "0.1",
    scalariformAutoformat := true,
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.apache.hadoop" % "hadoop-client" % "2.7.3",
      "org.apache.parquet" % "parquet-avro" % "1.8.1",
      "org.apache.parquet" % "parquet-hadoop" % "1.8.1"
    )
  )
