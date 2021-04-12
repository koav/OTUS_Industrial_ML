package otus.lessons.hw01

import otus.lessons.hw01.business.{Dataset, Regressor, RegressorType}
import com.github.tototoshi.csv._

import java.io._
import java.util.NoSuchElementException
import breeze.linalg._
import breeze.numerics._
import breeze.stats.regression._
import otus.lessons.hw01.business.RegressorType.RegressorType

import scala.io.Source

object Main extends App {

  println("== Regression demo started == ")


  println("\n01. Load dataset file\n")

  val file = getClass.getClassLoader.getResource("Dataset.csv").getPath
  val ds = new Dataset()
  val d = ds.read(file)

  println("       file: " +  file)
  println("    records: " + d.length)
  println(" first line: " + d.head)
  println("  last line: "  + d.last)
  println("       Note: The last column is the target")


  println("\n02. Split dataset into a train/test\n")

  val sets = ds.split(d)
  val train = ds.convert(sets._1)
  val test = ds.convert(sets._2)

  println("      train: " + train.rows + " x " + train.cols)
  println("       test: "  + test.rows + " x " + test.cols)

  println("\n02. Split dataset into a train/test\n")

  println("\n== Regression demo completed == ")
}