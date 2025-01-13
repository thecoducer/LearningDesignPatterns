package org.thecoducer.strategy.strategy;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.strategy.dto.PaymentDetails;

@Slf4j
public class PayByCardStrategy implements PayStrategy {

  @Override
  public void pay(PaymentDetails paymentDetails) {
    log.info("{} paid by Card.", paymentDetails.getAmountToBePaid());
  }
}
