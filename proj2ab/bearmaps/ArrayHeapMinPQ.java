package bearmaps;

import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private class Node<t> {
        t item;
        double priority;

        public Node(t item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    /**
     * Binary heap using 0-based indexing.
     */
    private Node<T>[] queue;
    private int size;
    private int heapCapacity;

    public ArrayHeapMinPQ(int capacity) {
        this.heapCapacity = capacity;
        queue = (Node<T>[]) new Node[heapCapacity];
        size = 0;

    }

    public ArrayHeapMinPQ() {
        this(8);
    }

    /**
     * Compare the priority of Nodes at pos i and pos j.
     *
     * @param i index i
     * @param j index j
     * @return arr[i].priority ( less than / equals / bigger than) arr[j].priority --> (-1 / 0 / 1)
     */
    private int comparePriority(int i, int j) {
        return Double.compare(queue[i].priority, queue[j].priority);
    }

    private void resize(int newHeapCapacity) {
        Node<T>[] newArr = (Node<T>[]) new Node[newHeapCapacity];
        System.arraycopy(queue, 0, newArr, 0, size);
        queue = newArr;
        heapCapacity = newHeapCapacity;
    }

    /**
     * Move the Node n up along the binary heap until it's in the right place.
     *
     * @param k Node at position k has such low priority that it has to sift up.
     */
    private void siftUp(int k) {
        while (comparePriority(k, (k - 1) / 2) < 0) {
            exchange(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    /**
     * Move the Node n down along the binary heap until it's in the right place.
     *
     * @param k Node at position k has such high priority that it has to sift down.
     */
    private void siftDown(int k) {
        // In MinPQ, exchange arr[k] with its child with lower priority.
        while (2 * k + 1 < size) {
            int smallerId = (2 * k + 2 >= size || comparePriority(2 * k + 1, 2 * k + 2) < 0) ? 2 * k + 1 : 2 * k + 2;
            if (comparePriority(k, smallerId) > 0) {
                exchange(k, smallerId);
            }
            k = smallerId;
        }
    }

    /**
     * Exchange the nodes at position i and position j.
     *
     * @param i pos i
     * @param j pos j
     */
    private void exchange(int i, int j) {
        Node<T> t = queue[i];
        queue[i] = queue[j];
        queue[j] = t;
    }

    @Override
    public void add(T item, double priority) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (contains(item)) {
            throw new RuntimeException("Try to add duplicate item into queue");
        }

        if (this.size == this.heapCapacity) {
            this.resize(2 * heapCapacity);
        }

        queue[size] = new Node<T>(item, priority);
        siftUp(size);
        size += 1;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    private int indexOf(T item) {
        if (item != null) {
            for (int i = 0; i < size; i++) {
                if (queue[i].item.equals(item)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public T getSmallest() {
        if (this.size == 0) {
            throw new RuntimeException("ArrayHeapMinPQ is empty");
        }
        return queue[0].item;
    }

    @Override
    public T removeSmallest() {
        if (this.size == 0) {
            throw new RuntimeException("Try to remove item from empty ArrayHeapMinPQ");
        }

        if (this.size <= 0.25 * heapCapacity && this.size >= 8) {
            this.resize(heapCapacity / 2);
        }

        T returnItem = queue[0].item;
        queue[0] = queue[size - 1];
        queue[size - 1] = null;
        size -= 1;
        siftDown(0);

        return returnItem;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Change the priority of given item in the queue, throw Exception if the given item is not in the queue or is null.
     *
     * @param item     The given item, must not be null
     * @param priority The new priority of item
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item) || item == null) {
            throw new NoSuchElementException();
        }
        int index = indexOf(item);
        double oldPriority = queue[index].priority;
        queue[index].priority = priority;
        int cmp = Double.compare(oldPriority, priority);
        if (cmp > 0) {// priority decreases
            siftUp(index);
        } else if (cmp < 0) {
            siftDown(index);
        }

    }
}
