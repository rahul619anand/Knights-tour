package org.learn

import scala.collection.mutable

object Main extends App {

  val boardDimension = Coordinate(10, 10)
  val startTile = Coordinate(0, 0)

  val algorithm = new Warnsdorff(boardDimension)
  val path = algorithm.findCompletePath(startTile)

  val array = asArray(path, boardDimension)
  printGrid(array)

  def asArray(
      path: mutable.LinkedHashSet[Coordinate],
      dimension: Coordinate
  ): Array[Array[Int]] = {
    val array = Array.ofDim[Int](dimension.X, dimension.Y)
    var index = 0
    path.foreach { tile =>
      index = index + 1
      array(tile.Y)(tile.X) = index
    }
    array
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
