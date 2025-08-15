package org.thecoducer.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCacheUsingCustomDataStructures<K, V> implements Cache<K, V> {

  static class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;
    int accessCount;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      this.accessCount = 1;
    }
  }

  static class DoublyLinkedList<K, V> {
    final Node<K, V> head;
    final Node<K, V> tail;
    int size;

    public DoublyLinkedList() {
      this.head = new Node(0, 0);
      this.tail = new Node(0, 0);
      head.next = tail;
      tail.prev = head;
      this.size = 0;
    }

    public void addToHead(Node<K, V> node) {
      head.next.prev = node;
      node.next = head.next;

      head.next = node;
      node.prev = head;

      size++;
    }

    public void remove(Node<K, V> node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;

      size--;
    }
  }

  private final int capacity;
  private final Map<K, Node<K, V>> lfuCache;
  private final Map<Integer, DoublyLinkedList<K, V>> frequencyToNodesMap;
  private int leastFrequency;

  public LFUCacheUsingCustomDataStructures(int capacity) {
    this.capacity = capacity;
    this.lfuCache = new HashMap<>();
    this.frequencyToNodesMap = new HashMap<>();
  }

  @Override
  public V get(K key) {
    Node<K, V> node = lfuCache.get(key);
    if (node == null) return null;

    updateFrequencyAndLRUOrder(node);
    return node.value;
  }

  @Override
  public void put(K key, V value) {
    if (capacity == 0) return;

    if (lfuCache.containsKey(key)) {
      Node node = lfuCache.get(key);
      node.value = value;
      lfuCache.put(key, node);

      updateFrequencyAndLRUOrder(node);
      return;
    }

    if (lfuCache.size() >= capacity) {
      DoublyLinkedList<K, V> list = frequencyToNodesMap.get(leastFrequency);
      Node nodeToRemove = list.tail.prev;
      list.remove(nodeToRemove);
      lfuCache.remove(nodeToRemove.key);
    }

    Node newNode = new Node(key, value);
    lfuCache.put(key, newNode);
    frequencyToNodesMap.computeIfAbsent(1, k -> new DoublyLinkedList<>()).addToHead(newNode);
    leastFrequency = 1;
  }

  @Override
  public void remove(K key) {
    if (lfuCache.containsKey(key)) {
      Node node = lfuCache.get(key);
      lfuCache.remove(node.key);
      DoublyLinkedList<K, V> list = frequencyToNodesMap.get(node.accessCount);
      list.remove(node);

      if (list.size == 0 && node.accessCount == leastFrequency) {
        frequencyToNodesMap.remove(node.accessCount);
        leastFrequency = frequencyToNodesMap.keySet().stream().min(Integer::compareTo).orElse(1);
      }
    }
  }

  private void updateFrequencyAndLRUOrder(Node node) {
    DoublyLinkedList<K, V> oldList = frequencyToNodesMap.get(node.accessCount);
    oldList.remove(node);

    // If the list for the current frequency is empty and this was the least frequency,
    // update leastFrequency to reflect the new minimum frequency in the cache.
    if (oldList.size == 0 && node.accessCount == leastFrequency) {
      frequencyToNodesMap.remove(node.accessCount);
      leastFrequency++;
    }

    node.accessCount++;
    frequencyToNodesMap
        .computeIfAbsent(node.accessCount, k -> new DoublyLinkedList<>())
        .addToHead(node);
  }
}
