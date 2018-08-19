package org.solve.pawnstour.domain.exception

import org.solve.pawnstour.service.PawnsTour.Path

/** Custom Exception for breaking from recursion when a tour is found */
final case class PawnTourFound(tour: Path) extends Exception
