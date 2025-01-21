package org.thecoducer.prototype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer implements Cloneable {
  private String name;
  private String emailId;
  private String phoneNumber;
  private Address address;

  // all args constructor
  public Customer(String name, String emailId, String phoneNumber, Address address) {
    this.name = name;
    this.emailId = emailId;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  // copy constructor
  public Customer(Customer customer) {
    this.name = customer.name;
    this.emailId = customer.emailId;
    this.phoneNumber = customer.phoneNumber;
    this.address = new Address(customer.address);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
