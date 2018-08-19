package org.solve.knightstour.service

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

import org.solve.knightstour.domain.Coordinate
import org.solve.knightstour.domain.exception.{
  InvalidCoordinateException,
  KnightsTourException
}

/**
  * This class demonstrates Knights Tour on a N*N sized board
  *
  * It uses Warnsdorff's technique which does the following:
  * 1. Mark tiles when its visited.
  * 2. To decide next tile in path, look at all possible unmarked tiles based on moving rules.
  * 3. Rank each possibility by the number of possible next moves from that tile.
  * 4. Move to any tile with the lowest rank.
  *
  * @param boardDimension
  * @param legalMoves
  */
class WarnsdorffRuleTour(
    boardDimension: Coordinate,
    legalMoves: Set[Coordinate]
) extends KnightsTour {
  import KnightsTour._

  private val maxTiles: Int = maximumTiles(boardDimension)

  override def findCompletePath(startTile: Tile): Try[Path] = {
    if (startTile.isInBounds(boardDimension)) {
      val initialPath = mutable.LinkedHashSet(startTile)
      val completePath = path(initialPath)
      if (completePath.size == maxTiles)
        Success(completePath)
      else
        Failure(KnightsTourException("Complete path couldn't be found"))
    } else
      Failure(
        InvalidCoordinateException(
          "Start Tile not within board dimension bounds"
        )
      )
  }

  /** Recursively finds the complete path from a starting tile in the path */
  private def path(prevPath: Path): Path = {
    val nextMoves = nextRankedMoves(prevPath)
    if (nextMoves.isEmpty) prevPath
    else {
      val move = selectLowestRankedMove(nextMoves)
      val currentPath = prevPath + nextTile(prevPath.last, move)
      val nextPath = path(currentPath)
      if (nextPath.size == maxTiles || nextPath.size > prevPath.size)
        nextPath
      else prevPath
    }
  }

  /** Finds all possible unmarked tiles to move, based on moving rules. */
  private def nextRankedMoves(path: Path): List[RankedMove] = {
    val moves = allValidMoves(legalMoves, path, boardDimension)
    moves.map { move =>
      val nextPath = path + nextTile(path.last, move)
      val nextPathMoves = allValidMoves(legalMoves, nextPath, boardDimension)
      RankedMove(nextPathMoves.size, move)
    }.toList
  }

  /** Selects tile with lowest rank (i.e. tile with minimum number of possible next moves) */
  private def selectLowestRankedMove(moves: List[RankedMove]): Coordinate =
    moves.minBy(_.rank).move
}

//
//1 58 31  2 59 72 23 44 67 22
//92 18 15 93 19 14 61 20 13 46
//32  3 96 71 30 95 68 73 24 43
//16 57 89 17 60 88 28 45 66 21
//91 80 33 94 81 76 62 82 12 47
//37  4 97 70 29 98 69 74 25 42
//34 56 90 77 53 87 27 50 65  8
//100 79 36 99 84 75 63 83 11 48
//38  5 52 39  6 51 40  7 26 41
//35 55 85 78 54 86 10 49 64  9
