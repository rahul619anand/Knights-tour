package org.solve.pawnstour.service

import org.solve.pawnstour.UnitSpec
import org.solve.pawnstour.domain.Point

class WarnsdorffHeuristicSpec extends UnitSpec {
  import WarnsdorffHeuristic._
  import org.solve.fixtures._
  behavior of "Point"

  val pawnsTour: PawnsTour = new WarnsdorffHeuristic

  "findPath" should "return `InvalidPointException` with relative message if board dimension or starting point is invalid" in {
    pawnsTour
      .findPath(Point(11, 1), boardDimension, legalMoves)
      .failure
      .exception should have message InvalidStartPoint

    pawnsTour
      .findPath(Point(1, 1), inValidBoardDimension, legalMoves)
      .failure
      .exception should have message InvalidBoardDimension
  }

  "findPath" should "return `PawnTourNotFoundException` if it is unable to find a path" in {
    val exception = pawnsTour
      .findPath(Point(0, 2), (4, 4), legalMoves)
      .failure
      .exception

    exception should have message PathNotFound

  }

  "findPath" should "return complete tour as list of ordered Points that can be chained to form a path" in {
    pawnsTour
      .findPath(Point(1, 1), boardDimension, legalMoves)
      .success
      .value shouldEqual List(
      Point(0, 7),
      Point(2, 9),
      Point(5, 9),
      Point(3, 7),
      Point(5, 5),
      Point(7, 7),
      Point(4, 7),
      Point(2, 5),
      Point(2, 2),
      Point(4, 4),
      Point(2, 6),
      Point(0, 4),
      Point(3, 4),
      Point(5, 2),
      Point(7, 4),
      Point(5, 6),
      Point(5, 3),
      Point(2, 3),
      Point(0, 1),
      Point(3, 1),
      Point(1, 3),
      Point(3, 5),
      Point(6, 5),
      Point(4, 3),
      Point(7, 3),
      Point(9, 5),
      Point(9, 2),
      Point(7, 0),
      Point(4, 0),
      Point(1, 0),
      Point(3, 2),
      Point(6, 2),
      Point(8, 4),
      Point(5, 4),
      Point(5, 7),
      Point(8, 7),
      Point(6, 9),
      Point(9, 9),
      Point(9, 6),
      Point(6, 6),
      Point(4, 8),
      Point(7, 8),
      Point(7, 5),
      Point(4, 5),
      Point(2, 7),
      Point(0, 5),
      Point(0, 8),
      Point(3, 8),
      Point(1, 6),
      Point(1, 9),
      Point(4, 9),
      Point(4, 6),
      Point(6, 4),
      Point(6, 7),
      Point(8, 9),
      Point(8, 6),
      Point(6, 8),
      Point(9, 8),
      Point(7, 6),
      Point(7, 9),
      Point(9, 7),
      Point(9, 4),
      Point(9, 1),
      Point(6, 1),
      Point(8, 3),
      Point(8, 0),
      Point(5, 0),
      Point(7, 2),
      Point(4, 2),
      Point(2, 0),
      Point(0, 2),
      Point(2, 4),
      Point(2, 1),
      Point(5, 1),
      Point(8, 1),
      Point(6, 3),
      Point(3, 3),
      Point(0, 3),
      Point(0, 0),
      Point(3, 0),
      Point(1, 2),
      Point(1, 5),
      Point(1, 8),
      Point(3, 6),
      Point(1, 4),
      Point(1, 7),
      Point(3, 9),
      Point(0, 9),
      Point(0, 6),
      Point(2, 8),
      Point(5, 8),
      Point(8, 8),
      Point(8, 5),
      Point(8, 2),
      Point(6, 0),
      Point(9, 0),
      Point(9, 3),
      Point(7, 1),
      Point(4, 1),
      Point(1, 1)
    )

  }
}
