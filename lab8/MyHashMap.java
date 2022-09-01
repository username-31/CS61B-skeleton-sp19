import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * The number of key-value pairs in the Map.
     */
    private int size;
    /**
     * The length of the bucket array.
     */
    private int capacity;
    private final double loadFactor;

    /**
     * The linkedList-like key-value pair.
     */
    private static class entry<K, V> {
        private final K key;
        private V val;
        private entry<K, V> next;

        public entry(K key, V val, entry<K, V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    private entry<K, V>[] bucket;

    private Queue<K> keyQueue = new LinkedList<>();
    private Set<K> keySet = new HashSet<>();

    public MyHashMap() {
        size = 0;
        capacity = 16;
        loadFactor = 0.75;
        bucket = (entry<K, V>[]) new entry[capacity];
    }

    public MyHashMap(int initialSize) {
        if (initialSize <= capacity) throw new RuntimeException("IllegalInitialSize");
        capacity = initialSize;
        size = 0;
        loadFactor = 0.75;
        bucket = (entry<K, V>[]) new entry[capacity];

    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize <= 0 || loadFactor <= 0) throw new RuntimeException("IllegalInitialSize");
        size = 0;
        capacity = initialSize;
        this.loadFactor = loadFactor;
        bucket = (entry<K, V>[]) new entry[capacity];


    }

    /**
     * Calculate the index of the given key.
     *
     * @param key The given key.
     * @return The index of the given key.
     */
    private int hash(K key) {
        return hash(key, capacity);
    }

    private int hash(K key, int modulus) {
        return (key.hashCode() & 0x7FFFFFFF) % (modulus);
    }


    @Override
    public void clear() {
        size = 0;
        capacity = 16;
        bucket = (entry<K, V>[]) new entry[capacity];

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new RuntimeException("Null key is not allowed.");
        if (bucket[hash(key)] != null) {
            entry<K, V> currEntry = bucket[hash(key)];
            while (currEntry != null) {
                if (currEntry.key.equals(key)) return true;
                currEntry = currEntry.next;
            }
            return false;
        }// else bucket[hash(key)] is null
        return false;

    }

    @Override
    public V get(K key) {
        if (key == null) throw new RuntimeException("Null key is not allowed.");
        if (containsKey(key)) {
            entry<K, V> currEntry = bucket[hash(key)];
            while (currEntry != null) {
                if (currEntry.key.equals(key)) return currEntry.val;
                currEntry = currEntry.next;
            }
        }// else the map does not contain the key
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void resize(int newCapacity) {
        //TODO
        entry<K, V>[] newBucket = (entry<K, V>[]) new entry[newCapacity];
        for (int i = 0; i < capacity; i++) {
            if (bucket[i] != null) {
                if (bucket[i] != null) {
                    newBucket[hash(bucket[i].key, newCapacity)] = bucket[i];
                }
            }
        }
        bucket = newBucket;
        this.capacity = newCapacity;
    }

    @Override
    public void put(K key, V value) {
        if (this.size() >= loadFactor * capacity) {
            this.resize(2 * capacity);
        }

        if (containsKey(key)) {
            entry<K, V> currEntry = bucket[hash(key)];
            while (currEntry != null) {
                if (currEntry.key.equals(key)) { // Update an existing value.
                    currEntry.val = value;
                    return;
                } else {
                    currEntry = currEntry.next;
                }
            }
        } else {// Add a new entry.
            entry<K, V> oldEntry = bucket[hash(key)];
            bucket[hash(key)] = new entry<>(key, value, oldEntry);
            this.size += 1;
            keySet.add(key);
            keyQueue.add(key);
        }

    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        if (this.size() == 0) {
            throw new RuntimeException("Call iterator on empty MyHashMap");
        }
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        public MyHashMapIterator() {
        }

        public K next() {
            return keyQueue.poll();
        }

        public boolean hasNext() {
            return !keyQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        Map61B<String, Integer> m = new MyHashMap<>();
        for (int i = 0; i < 16; i++) {
            m.put("OK[" + i + "]", i);
        }

        for ( int i = 0; i < 16; i++) {
            System.out.println(m.get("OK[" + i + "]"));
        }
    }
}
