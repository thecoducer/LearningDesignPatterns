package org.thecoducer.strategy.entity;

import org.thecoducer.strategy.dto.PaymentDetails;
import org.thecoducer.strategy.strategy.PayStrategy;

public class PaymentMode {
  PayStrategy payStrategy;

  PaymentMode(PayStrategy payStrategy) {
    this.payStrategy = payStrategy;
  }

  public void pay(PaymentDetails paymentDetails) {
    payStrategy.pay(paymentDetails);
  }
}
