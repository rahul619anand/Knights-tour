package org.solve.pawnstour.domain.exception

/** Custom Exception representing invalid board coordinates */
final case class InvalidCoordinateException(message: String)
    extends Exception(message)
