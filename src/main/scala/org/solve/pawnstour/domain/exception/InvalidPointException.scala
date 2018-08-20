package org.solve.pawnstour.domain.exception

/** Custom Exception representing invalid board points */
final case class InvalidPointException(message: String)
    extends Exception(message)
