package org.thecoducer.observer.event;

import lombok.Getter;

@Getter
public enum PriceUpdateEvent implements Event {
  INCREASE_IN_PRICE,
  DECREASE_IN_PRICE;
}
