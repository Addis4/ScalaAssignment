import Dependencies._
import CommonSettings._
import scoverage.ScoverageKeys

name := "Assignment"

version := "1.0"

scalaVersion := scala

lazy val root = (
  project.in(file(".")).settings(
    run := {
      (run in inventory in Compile).evaluated
    })
    aggregate (inventory)
  )
lazy val inventory = (
  baseProject("inventory")
    settings(libraryDependencies ++= compileDeps(inventoryDependencies) ++ testDeps(h2, scalaTest, mock, akkaTestKit, slickTest3, slickTest4),
    ScoverageKeys.coverageMinimum := 90,
    ScoverageKeys.coverageFailOnMinimum := true,
    ScoverageKeys.coverageExcludedPackages := "",
    ScoverageKeys.coverageEnabled := true
  ))

def compileDeps(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "compile")

def testDeps(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
