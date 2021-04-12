package otus.lessons.hw01.business

import breeze.linalg.Vector.castOps
import breeze.linalg._


class LinearRegression {

  var w: DenseVector[Double] = null
  var w0: Double = 0

  def fit(features: DenseMatrix[Double], target: DenseVector[Double]): Unit = {

    // The first column of the matrix fill with ones

     val matrixOnes = DenseVector.ones[Double](features(::,0).length)
     val X = DenseMatrix.horzcat(matrixOnes.asDenseMatrix.t, features)
     val y = target

    // regression
     val w: DenseVector[Double] = (inv(X.t * X) * X.t) * y

     // parameters
     this.w = w.slice(1, w.length)
     this.w0 = w.valueAt(0)

    println(this.w0)
  }

  def predict(features: DenseMatrix[Double]): DenseVector[Double] = {
    (features * this.w) + this.w0
  }
}

