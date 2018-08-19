package org.learn

import scala.collection.mutable

trait Algorithm {
  import Algorithm._

  type Path = mutable.LinkedHashSet[Coordinate]
  type Tile = Coordinate

  def boardDimension: Coordinate = Defaults.BoardDimension
  def findCompletePath(startTile: Tile): Path

  val maxElements = boardDimension.X * boardDimension.Y

  def allValidMovesFromPath(path: Path): Set[Coordinate] =
    legalMoves.values.filter(move => isValidMove(path, move)).toSet

  def nextTile(currentTile: Tile, move: Coordinate): Tile =
    Coordinate(currentTile.X + move.X, currentTile.Y + move.Y)

  private def isValidMove(
      currentPath: Path,
      nextMove: Coordinate
  ): Boolean = {
    val nextTileToCheck = nextTile(currentPath.last, nextMove)
    nextTileToCheck.isInBounds(boardDimension) && !currentPath.contains(
      nextTileToCheck
    )
  }

  private val legalMoves: Map[Direction, Coordinate] =
    Map(
      West -> Coordinate(0, -3),
      East -> Coordinate(0, 3),
      South -> Coordinate(3, 0),
      North -> Coordinate(-3, 0),
      SouthWest -> Coordinate(2, -2),
      SouthEast -> Coordinate(2, 2),
      NorthWest -> Coordinate(-2, -2),
      NorthEast -> Coordinate(-2, 2)
    )

}

object Algorithm {
  object Defaults {
    val BoardDimension = Coordinate(10, 10)
  }
}

final case class Coordinate(X: Int, Y: Int) {
  def isInBounds(boardDimension: Coordinate): Boolean =
    X >= 0 && X < boardDimension.X && Y >= 0 && Y < boardDimension.Y
}

sealed trait Direction

case object North extends Direction
case object South extends Direction
case object East extends Direction
case object West extends Direction
case object NorthEast extends Direction
case object SouthEast extends Direction
case object NorthWest extends Direction
case object SouthWest extends Direction
