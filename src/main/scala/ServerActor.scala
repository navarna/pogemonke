import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import scala.collection.mutable

class ServerActor(system:ActorSystem) extends Actor {
  var players: mutable.Map[String, Affichable] = new mutable.LinkedHashMap();

  // Central Map Element
  var affichables = MapGenerator.generateAffichables()
  var pokedex = MapGenerator.generatePokedex()
  
  def receive = {
    case NewGame(clientName) => {
      println("initiation client")
      context.actorSelection("/user/" + clientName)  ! ShowAllMapElement(affichables)
      system.eventStream.publish(UpdatePositions)
    }
    
    case UpdatePositions => {
      system.eventStream.publish(UpdatePositions)
    }
    
    case UpdatePosition => {
      println("treating moving a player ")
      system.eventStream.publish(UpdatePositions)
    }
  }
}