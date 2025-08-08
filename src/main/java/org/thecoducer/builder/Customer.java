package org.thecoducer.builder;

import lombok.Getter;

@Getter
public class Customer {
  private final int id;
  private final String name;
  private final String emailId;
  private final String phoneNumber;
  private final String website;

  private Customer(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.emailId = builder.emailId;
    this.phoneNumber = builder.phoneNumber;
    this.website = builder.website;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private int id;
    private String name;
    private String emailId;
    private String phoneNumber;
    private String website;

    public Builder id(int id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder emailId(String emailId) {
      this.emailId = emailId;
      return this;
    }

    public Builder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public Builder website(String website) {
      this.website = website;
      return this;
    }

    public Customer build() {
      // validations
      if (id <= 0) {
        throw new IllegalArgumentException("Id is required.");
      } else if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Name is required.");
      }
      return new Customer(this);
    }
  }
}
