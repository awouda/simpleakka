package com.jtm.training.simpleakka

import akka.actor._
import com.jtm.training.simpleakka.Bank._
import concurrent.duration.Duration
import scala.concurrent.duration._

class Customer(bank: ActorRef) extends Actor with ActorLogging {

  import Customer._
  import context.dispatcher

  val transactionTime = 1.seconds

  var balance = 0
  var transactionCnt = 0

  self ! StartBanking

  override def receive = {
    case Paid(amount) =>
      balance += amount
      transactionCnt += 1
      val deadline = transactionTime.fromNow
      while (deadline.hasTimeLeft()) {} // barkeeper is waisting time
      log.info("%s was paid to %s".format(amount, self))
      bank ! TransactionCount(transactionCnt)
    //context.system.scheduler.schedule(initialDelay, processTransactionDuration, bank, TransactionCount(transactionCnt))
    case Withdrawal(amount) =>
      balance -= amount
      transactionCnt += 1
      val deadline = transactionTime.fromNow
      while (deadline.hasTimeLeft()) {} // barkeeper is waisting time
      log.info("%s was withdrawn from %s".format(amount, self))
      bank ! TransactionCount(transactionCnt)
    case StopBanking =>
      log.info("%s stops banking" format self)
      context.stop(self)
      bank ! CusttomerLeft
    case StartBanking =>
      log.info("started banking. receiving start amount")
      self ! Paid(10)
      bank ! CustomerEntered

  }

}

object Customer {

  case class Paid(amount: Int)

  case class Withdrawal(amount: Int)

  case object StopBanking

  case object StartBanking

}
