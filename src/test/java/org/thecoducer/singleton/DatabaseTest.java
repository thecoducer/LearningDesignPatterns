package org.thecoducer.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DatabaseTest {

  @Test
  public void testSameInstance() {
    Database database1 = Database.getInstance();
    Database database2 = Database.getInstance();
    assertEquals(database1, database2);
  }
}
