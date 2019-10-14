import sbt._

object Dependencies {

  val scala: String = "2.12.8"
  val logbackClassicVersion: String = "1.2.3"
  val scalaTestVersion: String = "3.0.5"

  val slick: ModuleID = "com.typesafe.slick" %% "slick" % "3.3.2"
  val jdbcConnector: ModuleID = "mysql" % "mysql-connector-java" % "8.0.17"
  val slickHikariCP: ModuleID = "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2"
  val logback: ModuleID = "ch.qos.logback" % "logback-classic" % logbackClassicVersion
  val akka: ModuleID = "com.typesafe.akka" %% "akka-http" % "10.1.10"
  val akkaHttp: ModuleID = "com.typesafe.akka" %% "akka-http" % "10.1.10"
  val akkaStream: ModuleID = "com.typesafe.akka" %% "akka-stream" % "2.5.23"
  val sparayJson: ModuleID = "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10"

  /** Test libraries */
  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % scalaTestVersion

  val mock: ModuleID = "org.mockito" % "mockito-core" % "2.27.0"

  val inventoryDependencies: Seq[ModuleID] = Seq(logback, scalaTest, mock, slick, slickHikariCP, jdbcConnector, akka, akkaHttp, akkaStream, sparayJson)

}
