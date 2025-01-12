package org.thecoducer.strategypattern.entity;

import org.thecoducer.strategypattern.strategy.PayByUpiStrategy;

public class Upi extends PaymentMode {
  public Upi() {
    super(new PayByUpiStrategy());
  }
}
