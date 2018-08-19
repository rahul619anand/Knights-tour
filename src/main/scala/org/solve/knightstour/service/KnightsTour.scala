package org.solve.knightstour.service

import scala.collection.mutable
import scala.util.Try

import org.solve.knightstour.domain._

trait KnightsTour {
  import KnightsTour._
  def findCompletePath(startTile: Tile): Try[Path]
}

object KnightsTour {
  type Path = mutable.LinkedHashSet[Coordinate]
  type Tile = Coordinate

  /** Returns maximum tiles in a board based on boardDimension */
  def maximumTiles(boardDimension: Coordinate): Int =
    boardDimension.X * boardDimension.Y

  /** Returns next tile coordinates after a move is made from current tile */
  def nextTile(currentTile: Tile, move: Coordinate): Coordinate =
    Coordinate(currentTile.X + move.X, currentTile.Y + move.Y)

  /** Returns coordinates for all valid moves from last visited tile in a path */
  def allValidMoves(
      legalMoves: Set[Coordinate],
      path: Path,
      boardDimension: Coordinate
  ): Set[Coordinate] =
    legalMoves.filter(move => isValidMove(path, move, boardDimension))

  /** Returns if next move along a path is valid according to rules */
  private def isValidMove(
      currentPath: Path,
      nextMove: Coordinate,
      boardDimension: Coordinate
  ): Boolean = {
    val nextTileToCheck = nextTile(currentPath.last, nextMove)
    nextTileToCheck.isInBounds(boardDimension) && !currentPath.contains(
      nextTileToCheck
    )
  }

}
