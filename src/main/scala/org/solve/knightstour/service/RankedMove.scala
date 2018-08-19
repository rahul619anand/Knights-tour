package org.solve.knightstour.service

import org.solve.knightstour.domain.Coordinate

/** Represents a move along with rank
  * Here, rank is based on no of possible moves that can be made,
  * if this move was chosen as next move
  * */
final case class RankedMove(rank: Int, move: Coordinate)
