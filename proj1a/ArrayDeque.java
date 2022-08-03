public class ArrayDeque<T> {
    private int size;
    private final int INIT_CAPACITY = 8;
    private T[] q;// deque elements
    private int headPtr;
    private int rearPtr;

    public ArrayDeque() {//ctor
        q = (T[]) new Object[INIT_CAPACITY];
        headPtr = 3;
        rearPtr = 2;//Looks weird but it works
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 更改数组容量
     *
     * @param newCapacity 新数组的长度
     */
    private void resize(int newCapacity) {
        T[] new_q = (T[]) new Object[newCapacity];

        int firstHalf, secondHalf, latterPtr;
        if (rearPtr < headPtr) {//firstHalf = min {headPtr, rearPtr} + 1
            firstHalf = rearPtr + 1;
            secondHalf = q.length - headPtr;
            latterPtr = headPtr;
        } else {// rearPtr > headPtr
            firstHalf = headPtr + 1;
            secondHalf = q.length - rearPtr;
            latterPtr = rearPtr;
        }
        //Copy the 2 halves respectively.
        System.arraycopy(q, 0, new_q, 0, firstHalf);
        System.arraycopy(q, latterPtr, new_q, new_q.length - secondHalf, secondHalf);
        if (rearPtr > headPtr) {// rearrange the latter ptr
            rearPtr = new_q.length - secondHalf;
        } else {
            headPtr = new_q.length - secondHalf;
        }

        q = new_q;
    }


    public void addFirst(T item) {
        if (this.size() == q.length) {//装满了
            this.resize(2 * q.length);
        }

        headPtr -= 1;
        size += 1;
        if (headPtr == -1) {//headPtr out of boundary
            headPtr = q.length - 1;
        }
        q[headPtr] = item;

    }


    public void addLast(T item) {
        if (this.size() == q.length) {//装满了
            this.resize(2 * q.length);
        }
        rearPtr += 1;
        if (rearPtr == q.length) {
            rearPtr = 0;
        }

        q[rearPtr] = item;
        size += 1;

    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T headItem = q[headPtr];

        if (this.size() < 0.5 * q.length && q.length >= 16) {//装载率小于50%
            this.resize(q.length / 2);
        }

        q[headPtr] = null;//erase the headItem
        headPtr += 1;
        if (headPtr == q.length) {
            headPtr = 0;
        }

        size -= 1;
        return headItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T rearItem = q[rearPtr];

        if (this.size() < 0.5 * q.length && q.length >= 16) {
            this.resize(q.length / 2);
        }

        q[rearPtr] = null;//erase the rearItem
        rearPtr -= 1;
        if (rearPtr == 0) {
            rearPtr = q.length - 1;
        }

        size -= 1;
        return rearItem;
    }

    public T get(int index) {
        if (index > this.size()) {// No such item
            return null;
        }

        if (headPtr + index <= q.length - 1) {
            return q[headPtr + index];
        } else {//headPtr + index out of boundary
            return q[headPtr + index - q.length];
        }
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }

        if (headPtr <= rearPtr) {
            for (int i = headPtr; i <= rearPtr; i++) {
                System.out.print(q[i] + " ");
            }
        } else {//headPtr > rearPtr, print the 2 halves respectively.
            for (int i = headPtr; i <= q.length - 1; i++) {
                System.out.print(q[i] + " ");
            }
            for (int i = 0; i <= rearPtr; i++) {
                System.out.print(q[i] + " ");
            }
        }
    }


}
