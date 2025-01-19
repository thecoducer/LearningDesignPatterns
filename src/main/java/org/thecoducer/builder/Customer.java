package org.thecoducer.builder;

import lombok.Getter;

@Getter
public class Customer {
  private int id;
  private String name;
  private String emailId;
  private String phoneNumber;
  private String website;

  public static Customer builder() {
    return new Customer();
  }

  public Customer id(int id) {
    this.id = id;
    return this;
  }

  public Customer name(String name) {
    this.name = name;
    return this;
  }

  public Customer emailId(String emailId) {
    this.emailId = emailId;
    return this;
  }

  public Customer phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public Customer website(String website) {
    this.website = website;
    return this;
  }

  public Customer build() {
    return this;
  }
}
