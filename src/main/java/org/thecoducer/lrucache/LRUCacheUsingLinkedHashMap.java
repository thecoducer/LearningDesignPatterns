package org.thecoducer.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheUsingLinkedHashMap<K, V> implements Cache<K, V> {

  private final Map<K, V> lruCache;

  public LRUCacheUsingLinkedHashMap(int capacity) {
    this.lruCache =
        new LinkedHashMap<>(capacity, 0.75f, true) {
          @Override
          protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return this.size() > capacity;
          }
        };
  }

  @Override
  public V get(K key) {
    checkNullKey(key);
    return lruCache.get(key);
  }

  @Override
  public void put(K key, V value) {
    checkNullKey(key);
    checkNullValue(value);
    lruCache.put(key, value);
  }

  @Override
  public void remove(K key) {
    checkNullKey(key);
    lruCache.remove(key);
  }

  private void checkNullKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key must not be null.");
    }
  }

  private void checkNullValue(V value) {
    if (value == null) {
      throw new IllegalArgumentException("Value must not be null.");
    }
  }
}
