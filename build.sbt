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
    settings(libraryDependencies ++= compileDeps(inventoryDependencies) ++ testDeps(scalaTest, mock),
    ScoverageKeys.coverageMinimum := 95,
    ScoverageKeys.coverageFailOnMinimum := true,
    ScoverageKeys.coverageExcludedPackages := ""
  ))

def compileDeps(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "compile")

def testDeps(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
