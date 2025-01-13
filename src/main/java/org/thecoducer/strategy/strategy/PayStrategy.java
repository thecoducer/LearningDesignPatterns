package org.thecoducer.strategy.strategy;

import org.thecoducer.strategy.dto.PaymentDetails;

public interface PayStrategy {

  void pay(PaymentDetails paymentDetails);
}