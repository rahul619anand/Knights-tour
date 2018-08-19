package org.learn

import scala.collection.mutable

/**
  * This class demonstrates the knights tour on a NxN sized board.
  *
  * It uses H C Warnsdorff's technique which includes the following algorithm:
  * 1. Mark squares when you land on them.
  * 2. To decide where to land next, look at all the possible next unmarked
  * 	  moves. Rank each possibility by the number of possible next moves from
  * 	  that location. Move to the location with the lowest rank. In a tie,
  * 	  any of the lowest scoring locations will work.
  */
class Warnsdorff(dimension: Coordinate) extends Algorithm {

  override def boardDimension: Coordinate = dimension

  override def findCompletePath(tile: Tile): Path = {
    val initialPath = mutable.LinkedHashSet(tile)
    path(initialPath)
  }

  private def path(prevPath: Path): Path = {
    val nextMoves = nextRankedMoves(prevPath)
    if (nextMoves.isEmpty) prevPath
    else {
      val move = lowestRankedMove(nextMoves)
      val currentPath = prevPath + nextTile(prevPath.last, move)
      val nextPath = path(currentPath)
      if (nextPath.size == maxElements || nextPath.size > prevPath.size)
        nextPath
      else prevPath
    }
  }

  private def nextRankedMoves(path: Path): List[RankedMove] = {
    val moves = allValidMovesFromPath(path)
    moves.map { move =>
      val nextPath = path + nextTile(path.last, move)
      val nextPathMoves = allValidMovesFromPath(nextPath)
      RankedMove(nextPathMoves.size, move)
    }.toList
  }

  private def lowestRankedMove(moves: List[RankedMove]): Coordinate =
    moves.minBy(_.rank).move
}

final case class RankedMove(rank: Int, move: Coordinate)
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
