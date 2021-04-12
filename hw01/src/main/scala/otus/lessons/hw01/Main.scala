package otus.lessons.hw01

import otus.lessons.hw01.business.{Dataset, LinearRegression, Regressor, RegressorType}
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

  //val file = getClass.getClassLoader.getResource("Dataset.csv").getPath
  val file = "E:\\OTUS_Industrial_ML\\hw01\\src\\main\\datasets\\Dataset.csv"
  val ds = new Dataset()
  val d = ds.read(file)

  println("       file: " +  file)
  println("    records: " + d.length)
  println(" first line: " + d.head)
  println("  last line: "  + d.last)
  println("       Note: The last column is the target")


  println("\n02. Split dataset into a train/test\n")

  val (train, test) = ds.split(d)

  println("      ratio: 0.8")
  println("      train: " + train.size)
  println("       test: "  + test.size)

  println("\n03. Split datasets into a features/target\n")

  val (train_X, train_y) = ds.getFeaturesAndTarget(train)
  println("      train_X: " + train_X.cols + " x " + train_X.rows)
  println("      train_y: " + train_y.stride + " x " + train_y.length )

  val (test_X, test_y) = ds.getFeaturesAndTarget(test)
  println("      test_X: " + test_X.cols + " x " + test_X.rows)
  println("      test_y: " + test_y.stride + " x " + test_y.length )

  println("\n04. Linear regression\n")

  val model = new LinearRegression()
      model.fit(train_X, train_y)

  //val predict = model.predict(test_X)

  //println(predict.length)

  println("\n== Regression demo completed == ")
}