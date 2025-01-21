package org.thecoducer.prototype;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjectCloneTest {

  @Test
  public void testAddressObjectClone() {
    Address address = new Address("123", "Bangalore", "Karnataka", "India", "560001");
    Address clonedAddress = null;
    try {
      clonedAddress = (Address) address.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    // deep copy - address and clonedAddress are different objects
    assertNotEquals(address, clonedAddress);

    // modifying address object should not affect clonedAddress object now and vice versa
    clonedAddress.setPinCode("560056");
    assertNotEquals(address.getPinCode(), clonedAddress.getPinCode());
    address.setCity("Kolkata");
    assertNotEquals(address.getCity(), clonedAddress.getCity());
  }

  @Test
  public void testCustomerObjectClone() {
    Address address = new Address("123", "Bangalore", "Karnataka", "India", "560001");
    Customer customer = new Customer("Mayukh Datta", "mayukh@gmail.com", "9807899090", address);
    Customer clonedCustomer = null;
    try {
      clonedCustomer = (Customer) customer.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    // deep copy - customer and clonedCustomer are different objects
    assertNotEquals(customer, clonedCustomer);

    // modifying the Address object inside Customer affects clonedCustomer
    // because we are not cloning the Address object inside Customer
    // it has made a shallow copy of the Address object by copying the reference of Address object
    assertEquals(customer.getAddress(), clonedCustomer.getAddress());
    customer.getAddress().setPinCode("560056");
    assertEquals(customer.getAddress().getPinCode(), clonedCustomer.getAddress().getPinCode());
    clonedCustomer.getAddress().setCity("Hyderabad");
    assertEquals(customer.getAddress().getCity(), clonedCustomer.getAddress().getCity());
  }

  @Test
  public void testCustomerCopyConstructorClone() {
    Address address = new Address("123", "Bangalore", "Karnataka", "India", "560001");
    Customer customer = new Customer("Mayukh Datta", "mayukh@gmail.com", "9807899090", address);
    Customer clonedCustomer = new Customer(customer);

    // deep copy
    assertNotEquals(customer, clonedCustomer);
    assertNotEquals(customer.getAddress(), clonedCustomer.getAddress());

    // modifying the Address object inside Customer should not affect clonedCustomer
    customer.getAddress().setPinCode("560056");
    assertNotEquals(customer.getAddress().getPinCode(), clonedCustomer.getAddress().getPinCode());
    clonedCustomer.getAddress().setCity("Hyderabad");
    assertNotEquals(customer.getAddress().getCity(), clonedCustomer.getAddress().getCity());
  }

  @Test
  public void testAddressCopyConstructorClone() {
    Address address = new Address("123", "Bangalore", "Karnataka", "India", "560001");
    Address clonedAddress = new Address(address);

    // deep copy
    assertNotEquals(address, clonedAddress);

    // modifying address object should not affect clonedAddress object now and vice versa
    address.setCity("Kolkata");
    assertNotEquals(address.getCity(), clonedAddress.getCity());
    clonedAddress.setPinCode("560056");
    assertNotEquals(address.getPinCode(), clonedAddress.getPinCode());
  }
}
