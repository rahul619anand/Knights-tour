package org.solve.knightstour.domain

/** Position with X an Y values */
final case class Coordinate(X: Int, Y: Int) {

  /** Checks if position is within board boundaries */
  def isInBounds(boardDimension: Coordinate): Boolean =
    X >= 0 && X < boardDimension.X && Y >= 0 && Y < boardDimension.Y
}
