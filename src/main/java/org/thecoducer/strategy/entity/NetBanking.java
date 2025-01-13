package org.thecoducer.strategy.entity;

import org.thecoducer.strategy.strategy.PayByNetBankingStrategy;

public class NetBanking extends PaymentMode {

  public NetBanking() {
    super(new PayByNetBankingStrategy());
  }
}
