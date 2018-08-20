package org.solve.pawnstour.domain

import org.solve.pawnstour.UnitSpec

class PointSpec extends UnitSpec {
  import org.scalatest.prop.TableDrivenPropertyChecks._
  import org.solve.fixtures._

  behavior of "Point"

  "isInBounds" should "return `True` or `False` based on, point is within or outside board dimensions" in {
    forAll(points) { (point, isValid) =>
      point.isInBounds(boardDimension) shouldEqual isValid
    }
  }

  "+" should "lead a Point to a new Point when a move is applied on it" in {
    Point(1, 1) + (2, 2) shouldBe Point(3, 3)
  }

  "validMoves" should "return all valid moves from a point based on moving rules and board dimension" in {
    Point(1, 1).validMoves(legalMoves, boardDimension) shouldEqual Set(
      Point(4, 1),
      Point(1, 4),
      Point(3, 3)
    )
  }

}
