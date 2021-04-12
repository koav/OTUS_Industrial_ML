package otus.lessons.hw01.math

import breeze.linalg.{DenseVector, sum}
import breeze.numerics.sqrt
import otus.lessons.hw01.math.ScoreType.ScoreType

class Scorer {

  def Evaluate(predictions: DenseVector[Double], targets: DenseVector[Double], scoreType: ScoreType): Double ={
    scoreType match {
      case ScoreType.RMSE => {
        return sqrt(sum((predictions - targets)*:*(predictions - targets))/predictions.length)
      }
      case ScoreType.MSE => {
        return sum((predictions - targets)*:*(predictions - targets))/predictions.length
      }
    }
  }

}

object ScoreType extends Enumeration {
  type ScoreType = Value
  val RMSE, MSE = Value
}
