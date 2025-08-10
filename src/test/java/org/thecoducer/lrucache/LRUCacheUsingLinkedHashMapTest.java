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
}
