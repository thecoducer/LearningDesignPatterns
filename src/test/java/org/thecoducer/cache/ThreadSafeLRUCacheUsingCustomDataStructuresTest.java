package org.thecoducer.cache;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class ThreadSafeLRUCacheUsingCustomDataStructuresTest {

  @Test
  void testPutAndGet() {
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);
    assertEquals(1, cache.get("a"));
    assertEquals(2, cache.get("b"));
  }

  @Test
  void testUpdateValue() {
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    assertEquals(1, cache.get("a"));
    cache.put("a", 5);
    assertEquals(5, cache.get("a"));
  }

  @Test
  void testAccessOrderWhileUpdatingValue() {
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
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
    Cache<String, String> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
    cache.put("a", "Apple");
    cache.put("b", "Banana");
    assertEquals("Apple", cache.get("a"));
    assertEquals("Banana", cache.get("b"));
  }

  @Test
  void testRemove() {
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.remove("a");
    assertNull(cache.get("a"));
  }

  @Test
  void testLRUEviction() {
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
    cache.put("a", 1);
    cache.put("b", 2);
    cache.put("c", 3); // "a" should be evicted
    assertNull(cache.get("a"));
    assertEquals(2, cache.get("b"));
    assertEquals(3, cache.get("c"));
  }

  @Test
  void testWithNullKey() {
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
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
    Cache<String, Integer> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(2);
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              cache.put("a", null);
            });
    assertEquals("Value must not be null.", exception.getMessage());
  }

  @Test
  void testThreadSafety() throws InterruptedException {
    // Create an LRU cache with capacity 3
    Cache<String, String> cache = new ThreadSafeLRUCacheUsingCustomDataStructures<>(3);

    // Create a fixed thread pool of 5 threads
    ExecutorService executor = Executors.newFixedThreadPool(5);

    // Submit 5 tasks (one per thread)
    for (int i = 1; i <= 5; i++) {
      int threadId = i;

      executor.submit(
          () -> {
            // Each thread creates its own key-value pair
            String key = "key" + threadId;
            cache.put(key, "value" + threadId);
            System.out.println("Thread-" + threadId + " put " + key);

            // Each thread tries to read ALL 5 possible keys
            for (int j = 1; j <= 5; j++) {
              String k = "key" + j;
              String value = cache.get(k);
              System.out.println("Thread-" + threadId + " got " + k + ": " + value);
            }
          });
    }

    // Stop accepting new tasks
    executor.shutdown();

    // Wait until all threads finish (or max 1 min)
    executor.awaitTermination(1, TimeUnit.MINUTES);

    // After all threads finish, verify eviction policy
    int nonNullCount = 0;
    for (int i = 1; i <= 5; i++) {
      String value = cache.get("key" + i);

      // Count how many keys are still present
      if (value != null) {
        nonNullCount++;

        // Also verify data integrity (value must match original pattern)
        assertTrue(value.startsWith("value"));
      }
    }

    // Assert that only 3 keys remain (capacity limit of the LRU cache)
    assertEquals(3, nonNullCount);
  }
}
