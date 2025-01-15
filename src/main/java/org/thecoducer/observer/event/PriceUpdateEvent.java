package org.thecoducer.observer.event;

import lombok.Getter;

@Getter
public enum PriceUpdateEvent implements Event {
  INCREASED_IN_PRICE,
  DECREASE_IN_PRICE;
}
