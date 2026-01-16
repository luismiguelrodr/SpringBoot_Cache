package com.example.lru;

public class Node {
    K key;
    CacheEntry<V> cacheEntry;
    Node prev;
    Node next;

    public Node(K key, CacheEntry<V> cacheEntry) {
        this.key = key;
        this.cacheEntry = cacheEntry;
    }

    public K getKey() {
        return key;
    }

    public CacheEntry<V> getCacheEntry() {
        return cacheEntry;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    private void removeLRUEntry() {
        Node lru = tail.prev;      // Tail's previous is LRU
        if (lru != head) {         // Ensure list isn't empty
            cacheMap.remove(lru.key);
            removeNode(lru);       // Remove from linked list
        }
    }
    
    private void removeExpiredEntries() {
        cacheMap.entrySet().removeIf(entry -> {
            Node node = entry.getValue();
            if (node.cacheEntry.isExpired()) {
                removeNode(node);  // Remove from list
                return true;       // Remove from map
            }
            return false;
        });
    }
}
