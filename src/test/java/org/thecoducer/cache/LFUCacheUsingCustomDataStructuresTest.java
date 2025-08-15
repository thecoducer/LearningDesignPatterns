package org.thecoducer.cache;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LFUCacheUsingCustomDataStructuresTest {

  @Test
  void testGetUpdatesFrequencyAndReturnsValue() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);

    // Initial gets
    assertEquals(1, cache.get("a"));
    assertEquals(2, cache.get("b"));

    // Access "a" again to increase its frequency
    assertEquals(1, cache.get("a"));

    // Add "c", should evict "b" (least frequently used)
    cache.put("c", 3);

    assertNull(cache.get("b"));
    assertEquals(1, cache.get("a"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testPutEvictsLeastFrequentlyUsed() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("x", 10);
    cache.put("y", 20);

    // Access "x" twice, "y" once
    cache.get("x");
    cache.get("x");
    cache.get("y");

    // Add "z", should evict "y" (lower frequency)
    cache.put("z", 30);

    assertNull(cache.get("y"));
    assertEquals(10, cache.get("x"));
    assertEquals(30, cache.get("z"));
  }

  @Test
  void testPutUpdatesValueAndFrequency() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);

    cache.get("a"); // freq a:2, b:1

    cache.put("a", 100); // update value, should not reset frequency

    assertEquals(100, cache.get("a")); // freq a:3
    assertEquals(2, cache.get("b"));

    // Add "c", should evict "b"
    cache.put("c", 3);

    assertNull(cache.get("b"));
    assertEquals(100, cache.get("a"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testRemoveDeletesKeyAndUpdatesLeastFrequency() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(3);
    cache.put("a", 1);
    cache.put("b", 2);
    cache.put("c", 3);

    cache.get("a"); // freq a:2
    cache.get("b"); // freq b:2

    cache.remove("a");
    assertNull(cache.get("a"));
    assertEquals(2, cache.get("b"));
    assertEquals(3, cache.get("c"));

    cache.remove("b");
    assertNull(cache.get("b"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testRemoveNonExistentKeyDoesNothing() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.remove("b"); // Should not throw
    assertEquals(1, cache.get("a"));
  }

  @Test
  void testEvictionWithSameFrequencyUsesLRU() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);

    // Both have freq 1, access "a" to make it MRU among freq 1
    cache.get("a");

    // Add "c", should evict "b" (LRU among freq 1)
    cache.put("c", 3);

    assertNull(cache.get("b"));
    assertEquals(1, cache.get("a"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testPutOnFullCacheWithAllSameFrequencyEvictsLRU() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);

    // No gets, both freq 1, "b" is MRU, "a" is LRU
    cache.put("c", 3);

    assertNull(cache.get("a"));
    assertEquals(2, cache.get("b"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testGetOnNonExistentKeyReturnsNull() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    assertNull(cache.get("notfound"));
  }

  @Test
  void testPutAndRemoveAllKeys() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);

    cache.remove("a");
    cache.remove("b");

    assertNull(cache.get("a"));
    assertNull(cache.get("b"));

    // Add again after removal
    cache.put("c", 3);
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testZeroCapacityCacheDoesNotStoreAnything() {
    Cache<String, Integer> cache = new LFUCacheUsingCustomDataStructures<>(0);
    cache.put("a", 1);
    assertNull(cache.get("a"));
    cache.put("b", 2);
    assertNull(cache.get("b"));
    cache.remove("a"); // Should not throw
  }
}
