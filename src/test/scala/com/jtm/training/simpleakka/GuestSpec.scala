package com.typesafe.training.simpleakka

import org.scalatest.{ WordSpecLike, BeforeAndAfterAll }
import org.scalatest.matchers.MustMatchers
import akka.testkit.{ TestKit, TestActorRef }
import com.typesafe.training.simpleakka.Waiter.{ OrderDrink, DrinkServed }
import com.typesafe.training.simpleakka.Drink.PinaScalada
import akka.actor.{ Props, ActorSystem }
import scala.concurrent.duration._

class GuestSpec extends TestKit(ActorSystem("guest-spec")) with WordSpecLike with MustMatchers with BeforeAndAfterAll {

//  // testkit has already the system actor
//
//  "Sending DrinkServed to a Guest" should {
//    "increase the drinkCount" in {
//      val guest = TestActorRef(new Guest(PinaScalada, system.deadLetters, 1 second, true, 1))
//      val guestActor = guest.underlyingActor
//      guest ! DrinkServed(PinaScalada)
//      guestActor.drinkCount must be === 1
//
//    }
//  }
//
//  "Creating a guest" should {
//    "return an OrderedDrink to the Waiter" in {
//      val guest = system.actorOf(Props(new Guest(PinaScalada, testActor, 1 second, true, 1)))
//      expectMsg(OrderDrink(PinaScalada))
//    }
//  }
//
//  "Sending a DrinkServed to a Guest" should {
//    "return an OrderedDrink to the Waiter after finishDrinkDuration" in {
//      val guest = system.actorOf(Props(new Guest(PinaScalada, testActor, 1 second, true, 1)))
//      guest ! DrinkServed(PinaScalada)
//      expectMsg(OrderDrink(PinaScalada)) // because of the startup behaviour of the Guest
//      within(500 milliseconds, 2 second) {
//        expectMsg(OrderDrink(PinaScalada))
//      }
//    }
//  }
//
//  override def afterAll {
//    system.shutdown()
//  }

}
