package org.solve.knightstour.util

import scala.collection.mutable

import org.solve.knightstour.domain.Coordinate

/** Utility for presenting tour path */
object TourPathUtil {

  def pathAsArray(
      path: mutable.LinkedHashSet[Coordinate],
      dimension: Coordinate
  ): Array[Array[Int]] = {
    val array = Array.ofDim[Int](dimension.X, dimension.Y)
    path.zipWithIndex.foreach {
      case (tile, index) =>
        array(tile.Y)(tile.X) = index + 1
    }
    array
  }
}
