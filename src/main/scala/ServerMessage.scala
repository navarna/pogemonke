import scala.collection.mutable.ListBuffer

case class ShowAllMapElement(affichables: ListBuffer[Affichable])
case object UpdatePosition
case object UpdatePositions
case class NewGame(clientName: String)