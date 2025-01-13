package org.thecoducer.strategy.entity;

import org.thecoducer.strategy.strategy.PayByCardStrategy;

public class Card extends PaymentMode {

  public Card() {
    super(new PayByCardStrategy());
  }
}
