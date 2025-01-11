package org.thecoducer.strategypattern.strategy;

import org.thecoducer.strategypattern.dto.PaymentDetails;

public interface PayStrategy {

  void pay(PaymentDetails paymentDetails);
}