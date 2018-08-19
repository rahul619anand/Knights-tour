package org.solve.knightstour

import com.typesafe.scalalogging.LazyLogging
import org.solve.knightstour.domain._
import org.solve.knightstour.domain.exception.{
  InvalidCoordinateException,
  KnightsTourException
}
import org.solve.knightstour.service.{KnightsTour, WarnsdorffRuleTour}
import org.solve.knightstour.util.TourPathUtil

// logger
// test cases
// mutable
// constructor method think
// coordinate
object KnightsTourApp extends App with LazyLogging {

  // board dimensions as max X and Y values
  val boardDimension = Coordinate(10, 10)

  // starting position of Knight in the tour
  val startTile = Coordinate(9, 9)

  // all legal valid moves
  private val legalMoves: Set[Coordinate] =
    Set(
      Coordinate(0, -3), // West
      Coordinate(0, 3), // East
      Coordinate(3, 0), // South
      Coordinate(-3, 0), // North
      Coordinate(2, -2), // SouthWest
      Coordinate(2, 2), // SouthEast
      Coordinate(-2, -2), // NorthWest
      Coordinate(-2, 2) // NorthEast
    )

  val service: KnightsTour = new WarnsdorffRuleTour(boardDimension, legalMoves)

  service.findCompletePath(startTile).map {
    // success
    path =>
      val array = TourPathUtil.pathAsArray(path, boardDimension)
      printGrid(array)
  } recover {
    // failure
    case ex @ (_: InvalidCoordinateException | _: KnightsTourException) =>
      logger.error(ex.getMessage)
  }

  def printGrid(array: Array[Array[Int]]) = {
    val maxRow = array.length
    val maxCol = array(0).length
    for (row <- 0 until maxRow) {
      for (col <- 0 until maxCol) {
        print(s"${array(row)(col)} ");
      }
      println()
    }
  }
}
