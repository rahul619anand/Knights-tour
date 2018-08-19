package org.solve.pawnstour

import com.typesafe.scalalogging.LazyLogging
import org.solve.pawnstour.domain._
import org.solve.pawnstour.domain.exception.{
  InvalidCoordinateException,
  PawnTourException
}
import org.solve.pawnstour.service.{PawnsTour, WarnsdorffHeuristic}

object PawnsTourApp extends App with LazyLogging {
  import PawnsTour._

  logger.info("Starting Pawn's tour App")

  // board dimensions as N  * N
  val N = 10
  private val boardDimension: (X, Y) = (N, N)

  // starting position of pawn in the board
  private val startPoint = Point(9, 9)

  // all legal moves
  private val legalMoves: Set[(X, Y)] =
    Set(
      (0, -3), // West
      (0, 3), // East
      (3, 0), // South
      (-3, 0), // North
      (2, -2), // SouthWest
      (2, 2), // SouthEast
      (-2, -2), // NorthWest
      (-2, 2) // NorthEast
    )

  private val service: PawnsTour = new WarnsdorffHeuristic()

  service.findPath(startPoint, boardDimension, legalMoves).map { path =>
    logger.info(s"Pawn's tour path : \n $path")
    val array = pathAsArray(path, boardDimension)
    printAsGrid(array)
  } recover {
    case ex @ (_: InvalidCoordinateException | _: PawnTourException) =>
      logger.error(ex.getMessage)
  }

  private def printAsGrid(array: Array[Array[Int]]) = {
    logger.info("Pawn's tour path as grid")
    val maxRow = array.length
    val maxCol = array(0).length
    for (row <- 0 until maxRow) {
      for (col <- 0 until maxCol) {
        print(s"${array(row)(col)} \t");
      }
      println()
    }
  }
}
