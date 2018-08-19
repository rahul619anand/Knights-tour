package org.solve.pawnstour.service

import scala.util.{Failure, Success, Try}

import org.solve.pawnstour.domain.Point
import org.solve.pawnstour.domain.exception.{
  InvalidCoordinateException,
  PawnTourException,
  PawnTourFound
}

/**
  * Demonstrates Pawns Tour on a N*N sized board
  *
  * It uses Warnsdorff's technique which does the following:
  * 1. Start from any point on the board and mark it as visited.
  * 2. To decide next point on board, look at all possible unmarked points based on moving rules.
  * 3. Rank each possibility by the number of next moves from that point.
  * 4. Move to any point with the lowest rank.
  * 5. Add chosen point to pawn's tour path (i.e marked) and repeat the process from last chosen point.
  *
  */
class WarnsdorffHeuristic extends PawnsTour {
  import PawnsTour._

  override def findPath(
      startPoint: Point,
      boardDimension: (X, Y),
      legalMoves: Set[(X, Y)]
  ): Try[Path] = {
    if (boardDimension._1 < 1 || boardDimension._2 < 1) {
      Failure(
        InvalidCoordinateException(
          "Provide a valid board dimension"
        )
      )
    } else if (!startPoint.isInBounds(boardDimension)) {
      Failure(
        InvalidCoordinateException(
          "Starting point not within board dimension bounds"
        )
      )
    } else {
      try {
        findPathRec(
          startPoint,
          Set(startPoint),
          List(startPoint),
          boardDimension,
          legalMoves
        )
        Failure(PawnTourException("Unable to find a path for pawn's tour"))
      } catch {
        case t: PawnTourFound => Success(t.tour)
      }
    }

  }

  /** Find all valid UnVisited points */
  private def allValidUnvisitedPoints(
      point: Point,
      visited: Set[Point],
      boardDimension: (X, Y),
      legalMoves: Set[(X, Y)]
  ): List[Point] = {
    def filterNotVisited(p: Point): List[Point] =
      p.validMoves(legalMoves, boardDimension)
        .filter(!visited.contains(_))
        .toList
    // sort unvisited points by number of possible unvisited moves that can be taken from them
    filterNotVisited(point).sortBy(filterNotVisited(_).length)
  }

  /** Find path for pawn's tour recursively using Warnsdorff's technique */
  private def findPathRec(
      point: Point,
      visited: Set[Point],
      path: List[Point],
      boardDimension: (X, Y),
      legalMoves: Set[(X, Y)]
  ): Unit =
    if (visited.size == boardDimension._1 * boardDimension._2)
      // break when any complete tour path is discovered
      throw new PawnTourFound(path)
    else
      allValidUnvisitedPoints(point, visited, boardDimension, legalMoves)
        .foreach { move =>
          findPathRec(
            move,
            visited + move,
            move :: path,
            boardDimension,
            legalMoves
          )
        }

}
