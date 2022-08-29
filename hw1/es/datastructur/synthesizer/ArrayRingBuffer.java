package es.datastructur.synthesizer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Make sure to that this class and all of its methods are public
// Make sure to add the override tag for all overridden methods
// Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //  Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 1;
        last = 0;
        fillCount = 0;
    }

    /**
     * @return The size of the buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * @return The number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (this.isFull()) {
            try {
                throw new Exception("Overflow");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        last += 1;
        if (last >= rb.length) {
            last = 0;
        }
        rb[last] = x;
        fillCount += 1;

        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T removedItem = rb[first];
        rb[first] = null;
        first += 1;
        if (first >= rb.length) {
            first = 0;
        }
        fillCount -= 1;
        return removedItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    //  When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator()  {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        int cnt;
        int pos;
        public ArrayRingBufferIterator() {
            cnt = 0;
            pos = first;
        }
        public boolean hasNext() {
            return cnt < fillCount;
        }

        public T next() {
            if (isEmpty()) {
                throw new RuntimeException("Ring Buffer underflow");
            }

            T returnItem = rb[pos];
            pos += 1;
            if (pos >= rb.length) {
                pos = 0;
            }
            cnt += 1;

            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        T[] selfArr = (T[]) new Object[rb.length];
        int i = 0;
        for (T sA : this) {
            selfArr[i] = sA;
            i += 1;
        }

        int j = 0;
        for (T oA : (ArrayRingBuffer<T>) o) {
            if (!oA.equals(selfArr[j])) {
                return false;
            }
            j += 1;
        }
        return true;
    }
}
//  Remove all comments that say TODO when you're done.
