package org.thecoducer.strategypattern.entity;

import org.thecoducer.strategypattern.dto.PaymentDetails;
import org.thecoducer.strategypattern.strategy.PayStrategy;

public class PaymentMode {
  PayStrategy payStrategy;

  PaymentMode(PayStrategy payStrategy) {
    this.payStrategy = payStrategy;
  }

  public void pay(PaymentDetails paymentDetails) {
    payStrategy.pay(paymentDetails);
  }
}
