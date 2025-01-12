package org.thecoducer.strategypattern.entity;

import org.thecoducer.strategypattern.strategy.PayByNetBankingStrategy;

public class NetBanking extends PaymentMode {

  public NetBanking() {
    super(new PayByNetBankingStrategy());
  }
}
