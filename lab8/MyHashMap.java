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
    private double loadFactor;

    /**
     * The key-value pair.
     */
    private class entry {
        private K key;
        private V val;

        private entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private ArrayList<entry>[] bucket;
    private Set<K> keySet = new HashSet<>();

    public MyHashMap() {
        size = 0;
        capacity = 16;
        loadFactor = 0.75;
        bucket = (ArrayList<entry>[]) new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {//TODO: 这一块很重要！
            bucket[i] = new ArrayList<>();
        }
    }

    public MyHashMap(int initialSize) {
        if (initialSize <= capacity) throw new RuntimeException("IllegalInitialSize");
        capacity = initialSize;
        size = 0;
        loadFactor = 0.75;
        bucket = (ArrayList<entry>[]) new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            bucket[i] = new ArrayList<>();
        }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize <= 0 || loadFactor <= 0) throw new RuntimeException("IllegalInitialSize");
        size = 0;
        capacity = initialSize;
        this.loadFactor = loadFactor;
        bucket = (ArrayList<entry>[]) new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            bucket[i] = new ArrayList<>();
        }

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
        int hash = (key.hashCode() & 0x7FFFFFFF) % (modulus);
        return hash;
    }


    @Override
    public void clear() {
        size = 0;
        capacity = 16;
        bucket = (ArrayList<entry>[]) new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            bucket[i] = new ArrayList<>();
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new RuntimeException("Null key is not allowed.");
        if (!bucket[hash(key)].isEmpty()) {
            for (entry e : bucket[hash(key)]) {// Runtime: O(N)
                if (e.key.equals(key)) return true;
            }
        }
        return false;

    }

    @Override
    public V get(K key) {
        if (key == null) throw new RuntimeException("Null key is not allowed.");
        if (containsKey(key)) {
            for (entry e : bucket[hash(key)]) {
                if (e.key.equals(key)) return e.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void resize(int newCapacity) {
        ArrayList<entry>[] largerBucket = (ArrayList<entry>[]) new ArrayList[newCapacity];
        //TODO
        bucket = largerBucket;
        this.capacity = newCapacity;
    }

    @Override
    public void put(K key, V value) {
        if (this.size() >= loadFactor * capacity) {
            this.resize(2 * capacity);
        }

        if (containsKey(key)) {
            for (entry e : bucket[hash(key)]) {
                if (e.key.equals(key)) {//Update the existing value.
                    e.val = value;
                }
            }
        } else {// Add a new entry.
            bucket[hash(key)].add(new entry(key, value));
            this.size += 1;
            keySet.add(key);
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
        //TODO
        return null;
    }

    public static void main(String[] args) {
        Map61B<String, Integer> m = new MyHashMap<>();
//        m.put("OK", 1);
//        m.put("OK", 2);
//        m.put("O", 3);
        System.out.println(m.containsKey("OK"));
        System.out.println(m.containsKey("O"));
        System.out.println(m.containsKey("OB"));
    }

}
