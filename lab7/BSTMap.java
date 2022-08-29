import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {//This is essential!

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        private Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private Node root;

    public BSTMap() {
    }


    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node node, K key) {
        if (node == null) return false;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return containsKey(node.left, key);
        } else if (cmp > 0) {
            return containsKey(node.right, key);
        } else return true;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }


    private V get(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.val;

    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    @Override
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) return new Node(key, val, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val;//reset the val associated with key
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Set<K> keySet() {
        //todo
        return null;
    }

    @Override
    public V remove(K key) {
        return remove(root, key);
    }

    private V remove(Node node, K key) {
        //TODO
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to the specified value.
     *
     * @param key The key to remove.
     * @param val The value to remove.
     * @return The removed value.
     */
    @Override
    public V remove(K key, V val) {

        if (get(key).equals(val)) {
            return remove(key);
        } else {
            throw new RuntimeException("The value does not correspond to the key");
        }
    }

    public void printInOrder() {
        //todo
    }

    @Override
    public Iterator<K> iterator() {
        //todo
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private BSTMapIterator() {
            //todo
        }

        @Override
        public boolean hasNext() {
            //todo
            return false;
        }

        @Override
        public K next() {
            //todo
            return null;
        }
    }


    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<>();

        for (int i = 0; i < 10; i++) {
            b.put("hi" + i, i);
            System.out.println(b.get("hi" + i));
        }
    }

}
