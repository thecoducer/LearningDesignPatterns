package org.thecoducer.observerpattern.eventsubscriber;


import org.thecoducer.observerpattern.entity.Customer;

import java.util.List;

public interface EventSubscriber {
  void update(List<Customer> customers);
}
