package org.solve.knightstour.domain.exception

/** Custom Exception representing incomplete Knight's tour */
final case class KnightsTourException(message: String)
    extends Exception(message)
