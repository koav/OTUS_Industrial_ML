package otus.lessons.hw01.math

import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.stats.regression.{lasso, leastSquares}
import otus.lessons.hw01.math.BreezAlgorithm.BreezAlgorithm

/*
*  Breez regression implementation
* */
class BreezRegression{

  var coefficients: DenseVector[Double] = null

  def fit(features: DenseMatrix[Double], target: DenseVector[Double], algorithm: BreezAlgorithm): Unit = {

    algorithm match {

      case BreezAlgorithm.SimpleRegression => {
        val model = leastSquares(features, target)
        this.coefficients = model.coefficients
      }

      case BreezAlgorithm.Lasso => {
        val lambda = 0.01
        val model = lasso(features, target, lambda)
        this.coefficients = model.coefficients
      }

    }
  }

  def predict(features: DenseMatrix[Double]): DenseVector[Double] = {
    (features * this.coefficients)
  }

}

object BreezAlgorithm extends Enumeration {
  type BreezAlgorithm = Value
  val SimpleRegression, Lasso  = Value
}
