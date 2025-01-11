package org.thecoducer.strategypattern.service;

import org.thecoducer.strategypattern.dto.PaymentDetails;

public class PaymentService {

  public void processPayment(PaymentDetails paymentDetails) {
    paymentDetails.getPaymentMode().pay(paymentDetails);
  }
}