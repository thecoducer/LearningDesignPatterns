package org.thecoducer.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerTest {

  @Test
  public void buildCustomer() {
    int id = 1;
    String name = "Mayukh Datta";
    String emailId = "mayukh@gmail.com";
    String phoneNumber = "1234567890";
    String website = "https://thecoducer.com";
    Customer customer =
        Customer.builder()
            .id(id)
            .name(name)
            .emailId(emailId)
            .phoneNumber(phoneNumber)
            .website(website)
            .build();

    assertEquals(id, customer.getId());
    assertEquals(name, customer.getName());
    assertEquals(emailId, customer.getEmailId());
    assertEquals(phoneNumber, customer.getPhoneNumber());
    assertEquals(website, customer.getWebsite());
  }
}
