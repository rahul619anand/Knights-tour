package org.solve.pawnstour.domain.exception

/** Custom Exception representing incomplete Pawn's tour */
final case class PawnTourException(message: String) extends Exception(message)
