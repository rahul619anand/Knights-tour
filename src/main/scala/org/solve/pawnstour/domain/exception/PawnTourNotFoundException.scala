package org.solve.pawnstour.domain.exception

/** Custom Exception representing incomplete Pawn's tour */
final case class PawnTourNotFoundException(message: String)
    extends Exception(message)
