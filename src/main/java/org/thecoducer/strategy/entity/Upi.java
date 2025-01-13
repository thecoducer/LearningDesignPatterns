package org.thecoducer.strategy.entity;

import org.thecoducer.strategy.strategy.PayByUpiStrategy;

public class Upi extends PaymentMode {
  public Upi() {
    super(new PayByUpiStrategy());
  }
}
