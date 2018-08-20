package org.solve.pawnstour.domain

/** Point with X an Y values */
final case class Point(x: Int, y: Int) {
  import org.solve.pawnstour.service.PawnsTour._

  /** Checks if this Point is within board boundaries */
  def isInBounds(boardDimension: (X, Y)): Boolean =
    x >= 0 && x < boardDimension._1 && y >= 0 && y < boardDimension._2

  /** Makes a move on this point to get to the next Point */
  def +(move: (X, Y)): Point =
    Point(x + move._1, y + move._2)

  /** Returns all valid moves from this Point */
  def validMoves(legalMoves: Set[(X, Y)], boardDimension: (X, Y)): Set[Point] =
    legalMoves
      .flatMap { move =>
        Set(this + move)
      }
      .filter(_.isInBounds(boardDimension))
}
