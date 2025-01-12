package org.thecoducer.strategypattern.entity;

import org.thecoducer.strategypattern.strategy.PayByCardStrategy;

public class Card extends PaymentMode {

  public Card() {
    super(new PayByCardStrategy());
  }
}
