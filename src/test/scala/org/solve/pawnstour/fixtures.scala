package org.solve

import org.solve.pawnstour.domain.Point
import org.solve.pawnstour.service.PawnsTour.{X, Y}

object fixtures {
  val legalMoves: Set[(X, Y)] =
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

  val boardDimension: (X, Y) = (10, 10)
  val inValidBoardDimension: (X, Y) = (-10, -10)

  import org.scalatest.prop.TableDrivenPropertyChecks._

  val points =
    Table(
      ("point", "isValid"),
      (Point(-1, 2), false),
      (Point(10, 10), false),
      (Point(-1, -2), false),
      (Point(3, Integer.MIN_VALUE), false),
      (Point(Integer.MIN_VALUE, 3), false),
      (Point(1, 2), true),
      (Point(5, 5), true),
      (Point(9, 9), true)
    )
}
