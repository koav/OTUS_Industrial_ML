package otus.lessons.hw01.business

import com.github.tototoshi.csv._

import java.io._
import breeze.linalg._
import otus.lessons.hw01.Main.d

import scala.util.Random

/*
*  Class for loading and processing a dataset
* */
class Dataset extends IDataset{

  /*
  * Reading a CSV files
  * */
  override def read(pathToFile: String): List[List[Double]] = {

    require(requirement = !pathToFile.isNullOrEmpty, message = "Specify the datasource file name")

    try {

      val data = CSVReader.open(new File(pathToFile)).all()

      // remove header
      val dataNoHeader = data.slice(1, data.length)

      // convert to Double
      dataNoHeader.map(_.map(_.toDoubleOption.getOrElse(0.0)))

    } catch {
      case e: FileNotFoundException => { println("Couldn't find dataset file."); throw e; }
    }
  }

  /*
  * Split the set into a training set and a test set with ratio
  * */
  override def split(data: List[List[Double]], ratio: Double = 0.8, seed: Int = 42): (List[List[Double]], List[List[Double]]) = {

    require(!data.isEmpty, message = "The set cannot be empty")

    Random.setSeed(seed)

    val dataShuffled = Random.shuffle(data)
    val (train, test) = dataShuffled.splitAt((ratio * dataShuffled.length).toInt)

    (train, test)
  }

  /*
  *  Get features and target
  * */
  override def getFeaturesAndTarget(data: List[List[Double]]): (DenseMatrix[Double], DenseVector[Double]) = {

    val matrix = DenseMatrix(data.map(x=> x.toArray):_*)

    val features = matrix(::, 0 to (matrix.cols - 2))
    val target = matrix(::, (matrix.cols - 1))

    (features, target)
  }
}

trait IDataset{
  def read(pathToFile: String): List[List[Double]]
  def split(data: List[List[Double]], ratio: Double, seed: Int): (List[List[Double]], List[List[Double]])
  def getFeaturesAndTarget(data: List[List[Double]]): (DenseMatrix[Double], DenseVector[Double])
}