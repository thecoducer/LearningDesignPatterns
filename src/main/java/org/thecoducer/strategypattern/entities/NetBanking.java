package org.thecoducer.strategypattern.entities;

import org.thecoducer.strategypattern.strategy.PayByNetBankingStrategy;

public class NetBanking extends PaymentMode {

  public NetBanking() {
    super(new PayByNetBankingStrategy());
  }
}
