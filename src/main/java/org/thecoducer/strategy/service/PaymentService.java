package org.thecoducer.strategy.service;

import org.thecoducer.strategy.dto.PaymentDetails;

public class PaymentService {

  public void processPayment(PaymentDetails paymentDetails) {
    paymentDetails.getPaymentMode().pay(paymentDetails);
  }
}