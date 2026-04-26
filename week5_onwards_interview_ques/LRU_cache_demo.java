package week5_onwards_interview_ques;
import java.util.HashMap;

class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev, next;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache<K, V> {
    private final int capacity;
    private final HashMap<K, Node<K, V>> cache;
    private final Node<K, V> head, tail; // dummy nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) return null;
        Node<K, V> node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node<K, V> node = new Node<>(key, value);
            cache.put(key, node);
            addToHead(node);
            if (cache.size() > capacity) {
                Node<K, V> lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }
        }
    }

    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }
}
public class LRU_cache_demo {
    
}
