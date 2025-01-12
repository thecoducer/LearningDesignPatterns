package org.thecoducer.strategypattern.dto;

import org.thecoducer.strategypattern.entity.PaymentMode;

public class PaymentDetails {
  private PaymentMode paymentMode;
  private double amountToBePaid;

  public PaymentMode getPaymentMode() {
    return paymentMode;
  }

  public void setPaymentMode(PaymentMode paymentMode) {
    this.paymentMode = paymentMode;
  }

  public double getAmountToBePaid() {
    return amountToBePaid;
  }

  public void setAmountToBePaid(double amountToBePaid) {
    this.amountToBePaid = amountToBePaid;
  }
}
