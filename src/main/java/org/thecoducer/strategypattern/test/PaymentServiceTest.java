package org.thecoducer.strategypattern.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.thecoducer.strategypattern.dto.PaymentDetails;
import org.thecoducer.strategypattern.entities.Card;
import org.thecoducer.strategypattern.entities.NetBanking;
import org.thecoducer.strategypattern.entities.Upi;
import org.thecoducer.strategypattern.service.PaymentService;
import org.thecoducer.strategypattern.strategy.PayByCardStrategy;
import org.thecoducer.strategypattern.strategy.PayByNetBankingStrategy;
import org.thecoducer.strategypattern.strategy.PayByUpiStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceTest {
  private final PaymentService paymentService = new PaymentService();

  @Test
  public void payByUpiTest() {
    LogCaptor logCaptor = LogCaptor.forClass(PayByUpiStrategy.class);
    PaymentDetails paymentDetails = new PaymentDetails();
    paymentDetails.setPaymentMode(new Upi());
    paymentDetails.setAmountToBePaid(2313.76);
    paymentService.processPayment(paymentDetails);
    assertEquals("2313.76 paid by UPI.", logCaptor.getInfoLogs().getFirst());
  }

  @Test
  public void payByCardTest() {
    LogCaptor logCaptor = LogCaptor.forClass(PayByCardStrategy.class);
    PaymentDetails paymentDetails = new PaymentDetails();
    paymentDetails.setPaymentMode(new Card());
    paymentDetails.setAmountToBePaid(107);
    paymentService.processPayment(paymentDetails);
    assertEquals("107.0 paid by Card.", logCaptor.getInfoLogs().getFirst());
  }

  @Test
  public void payByNetBankingTest() {
    LogCaptor logCaptor = LogCaptor.forClass(PayByNetBankingStrategy.class);
    PaymentDetails paymentDetails = new PaymentDetails();
    paymentDetails.setPaymentMode(new NetBanking());
    paymentDetails.setAmountToBePaid(9087);
    paymentService.processPayment(paymentDetails);
    assertEquals("9087.0 paid through Net Banking.", logCaptor.getInfoLogs().getFirst());
  }
}
