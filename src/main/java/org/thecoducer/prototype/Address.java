package org.thecoducer.prototype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address implements Cloneable {
  private String street;
  private String city;
  private String state;
  private String country;
  private String pinCode;

  // all args constructor
  public Address(String street, String city, String state, String country, String pinCode) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.pinCode = pinCode;
  }

  // copy constructor
  public Address(Address address) {
    this.street = address.street;
    this.city = address.city;
    this.state = address.state;
    this.country = address.country;
    this.pinCode = address.pinCode;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
