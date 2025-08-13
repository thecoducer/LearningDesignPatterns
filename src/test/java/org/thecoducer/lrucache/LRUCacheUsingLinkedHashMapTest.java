package org.thecoducer.lrucache;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LRUCacheUsingLinkedHashMapTest {

  @Test
  void testPutAndGet() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    cache.put("a", 1);
    cache.put("b", 2);
    assertEquals(1, cache.get("a"));
    assertEquals(2, cache.get("b"));
  }

  @Test
  void testUpdateValue() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    cache.put("a", 1);
    assertEquals(1, cache.get("a"));
    cache.put("a", 5);
    assertEquals(5, cache.get("a"));
  }

  @Test
  void testAccessOrderWhileUpdatingValue() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    cache.put("a", 1);
    cache.put("b", 2);
    // The current order is b, a
    // After updating a's value, the order should become a, b
    cache.put("a", 5);
    cache.put("c", 3);
    // "b" should get evicted by now, making the order c, a
    assertNull(cache.get("b"));
    assertEquals(5, cache.get("a"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testPutAndGetByStoringStringValues() {
    Cache<String, String> cache = new LRUCacheUsingLinkedHashMap<>(2);
    cache.put("a", "Apple");
    cache.put("b", "Banana");
    assertEquals("Apple", cache.get("a"));
    assertEquals("Banana", cache.get("b"));
  }

  @Test
  void testRemove() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    cache.put("a", 1);
    cache.remove("a");
    assertNull(cache.get("a"));
  }

  @Test
  void testLRUEviction() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    cache.put("a", 1);
    cache.put("b", 2);
    cache.put("c", 3); // "a" should be evicted
    assertNull(cache.get("a"));
    assertEquals(2, cache.get("b"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testWithNullKey() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              cache.put(null, 1);
            });
    assertEquals("Key must not be null.", exception.getMessage());
  }

  @Test
  void testWithNullValue() {
    Cache<String, Integer> cache = new LRUCacheUsingLinkedHashMap<>(2);
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              cache.put("a", null);
            });
    assertEquals("Value must not be null.", exception.getMessage());
  }
}
