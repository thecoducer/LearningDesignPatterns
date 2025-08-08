package org.thecoducer.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  void buildCustomer_success() {
    int id = 1;
    String name = "Mayukh Datta";
    String emailId = "mayukh@gmail.com";
    String phoneNumber = "1234567890";
    Customer customer =
        Customer.builder().id(id).name(name).emailId(emailId).phoneNumber(phoneNumber).build();

    assertEquals(id, customer.getId());
    assertEquals(name, customer.getName());
    assertEquals(emailId, customer.getEmailId());
    assertEquals(phoneNumber, customer.getPhoneNumber());
    assertNull(customer.getWebsite());
  }

  @Test
  void customerWithInvalidId() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Customer.builder().name("Mayukh").build();
            });
    assertEquals("Id is required.", exception.getMessage());
  }

  @Test
  void customerWithBlankName() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Customer.builder().id(2134).build();
            });
    assertEquals("Name is required.", exception.getMessage());
  }
}
