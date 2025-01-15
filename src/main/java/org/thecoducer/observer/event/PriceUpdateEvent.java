package org.thecoducer.observer.event;

import lombok.Getter;

@Getter
public enum PriceUpdateEvent implements Event {
  PRICE_INCREASED,
  PRICE_DECREASED;
}
