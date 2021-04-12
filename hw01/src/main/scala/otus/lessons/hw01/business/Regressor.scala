package otus.lessons.hw01.business

import com.github.tototoshi.csv._

import java.io._
import java.util.NoSuchElementException
import breeze.linalg._
import breeze.numerics._
import breeze.stats.regression._
import otus.lessons.hw01.business.RegressorType.{Breez, RegressorType}

import scala.io.Source

class Regressor {

  def Train(regressorType: RegressorType, data_X: DenseVector[Double], data_y: DenseVector[Double])
  : DenseMatrix[Double] = {

    regressorType match {
      case RegressorType.Breez => this.BreezLinearRegression(data_X, data_y)
      case RegressorType.Custom => this.CustomLinearRegression(data_X, data_y)
    }
  }

  protected def BreezLinearRegression(data_X: DenseVector[Double], data_y: DenseVector[Double])
  : DenseMatrix[Double] = {
    val model = leastSquares(data_X, data_y)
  }

  protected def CustomLinearRegression(data_X: DenseVector[Double], data_y: DenseVector[Double])
  : DenseMatrix[Double] = {

  }
}

object RegressorType extends Enumeration {
  type RegressorType = Value
  val Breez, Custom = Value
}
