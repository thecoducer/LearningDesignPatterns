package org.thecoducer.lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeLRUCacheUsingCustomDataStructures<K, V> implements Cache<K, V> {

  class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> prev, next;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private final int capacity;
  private final Map<K, Node<K, V>> lruCache;
  private Node<K, V> head;
  private Node<K, V> tail;

  private final Lock lock = new ReentrantLock();

  public ThreadSafeLRUCacheUsingCustomDataStructures(int capacity) {
    this.capacity = capacity;
    this.lruCache = HashMap.newHashMap(capacity);
    this.head = new Node(0, 0);
    this.tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
  }

  @Override
  public V get(K key) {
    lock.lock();
    try {
      checkNullKey(key);
      Node<K, V> node = lruCache.get(key);
      if (node == null) return null;
      moveToHead(node);
      return node.value;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void put(K key, V value) {
    lock.lock();
    try {
      checkNullKey(key);
      checkNullValue(value);

      if (lruCache.containsKey(key)) {
        Node node = lruCache.get(key);
        node.value = value;
        lruCache.put(key, node);
        moveToHead(node);
      } else {
        if (lruCache.size() >= capacity) {
          lruCache.remove(tail.prev.key);
          removeNode(tail.prev);
        }
        Node newNode = new Node(key, value);
        addToHead(newNode);
        lruCache.put(key, newNode);
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void remove(K key) {
    lock.lock();
    try {
      checkNullKey(key);
      if (lruCache.containsKey(key)) {
        Node node = lruCache.get(key);
        lruCache.remove(key);
        removeNode(node);
      }
    } finally {
      lock.unlock();
    }
  }

  private void moveToHead(Node<K, V> node) {
    removeNode(node);
    addToHead(node);
  }

  private void removeNode(Node<K, V> node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
  }

  private void addToHead(Node<K, V> node) {
    node.next = head.next;
    head.next.prev = node;

    head.next = node;
    node.prev = head;
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
