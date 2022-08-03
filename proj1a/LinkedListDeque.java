
public class LinkedListDeque<T> {
    private class node {
        private node prev;
        private T item;
        private node next;

        private node(node prev, T item, node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        private node(node prev, node next) {
            this.prev = prev;
            this.next = next;
        }

    }

    private int size;
    /**
     * Circular sentinel
     */
    private node sentinel;


    public LinkedListDeque() {
        size = 0;
        sentinel = new node(null, null);//这一行自己没有想到,如果没有的话会报nullPointerException
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        node first = new node(sentinel, item, sentinel.next);
        sentinel.next.prev = first; //Note that when empty, sentinel.next.prev is sentinel.prev
        sentinel.next = first;// 不可以和上一行交换位置，尽管这一行更加容易被先想到

    }

    public void addLast(T item) {
        size += 1;
        node last = new node(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        node currentNode = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(sentinel.next.item + " ");
            currentNode = currentNode.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        //else return the last node
        node first = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        //else return the last node
        node last = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return last.item;
    }

    public T get(int index) {
        if (isEmpty()) return null;

        node currentNode = sentinel.next;// 0 is the front, 1 is the next item
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

}
