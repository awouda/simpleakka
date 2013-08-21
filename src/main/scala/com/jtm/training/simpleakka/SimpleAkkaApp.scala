package com.jtm.training.simpleakka

import akka.actor._
import com.jtm.training.simpleakka.Customer._
import com.jtm.training.simpleakka.Bank._

object SimpleAkka extends App {

  implicit val system = ActorSystem("simple-akka-system")

  val numOfActors = system.settings.config getInt "simple-akka.runner.number-of-actors"

  val theBank = system.actorOf(Props(new Bank()), "bank")

  for (i <- 1 to numOfActors) {
    system.actorOf(Props(new Customer(theBank)), "customer" + i)
  }
}

class Bank() extends Actor with ActorLogging {

  val random = new util.Random()
  var clients = 0

  override def receive = {
    case CustomerEntered => clients += 1
    case CusttomerLeft =>
      clients -= 1
      clients match {
        case 0 => context.system.shutdown()
        case _ =>
      }

    case TransactionCount(cnt) => if (cnt > 10) {
      sender ! StopBanking
    } else {
      if (random.nextInt(10) % 2 == 0) {
        sender ! Paid(random.nextInt(100))
      } else {
        sender ! Withdrawal(random.nextInt(100))
      }

    }

  }
}

object Bank {

  case class TransactionCount(cnt: Int)
  case object CustomerEntered
  case object CusttomerLeft

}