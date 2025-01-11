package org.thecoducer.strategypattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.strategypattern.dto.PaymentDetails;

@Slf4j
public class PayByUpiStrategy implements PayStrategy {

  @Override
  public void pay(PaymentDetails paymentDetails) {
    log.info("{} paid by UPI.", paymentDetails.getAmountToBePaid());
  }
}
