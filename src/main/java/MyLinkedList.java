import java.util.NoSuchElementException;

/**
 * A doubly-linked list implementation of the MyList interface.
 * This implementation provides all the optional list operations and permits all elements
 * (including null elements, though they are rejected in this implementation).
 * The list maintains references to both the head (first) and tail (last) nodes
 * for efficient operations at both ends.
 *
 * @param <E> the type of elements held in this collection
 */
public class MyLinkedList<E> implements MyList<E> {

    /**
     * The first node in the linked list.
     */
    private Node<E> first;

    /**
     * The last node in the linked list.
     */
    private Node<E> last;

    /**
     * The number of elements in the linked list.
     */
    private int size = 0;

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @return true if the element was added successfully, false if the element is null
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        linkLast(element);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param element element to be removed from this list, if present
     * @return true if this list contained the specified element, false otherwise
     */
    @Override
    public boolean remove(E element) {
        if (element == null) {
            return false;
        } else {
            for (Node<E> current = first; current != null; current = current.next) {
                if (element.equals(current.data)) {
                    unlink(current);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> nodeByIndex = getNode(index);
        E oldVal = nodeByIndex.data;
        nodeByIndex.data = element;
        return oldVal;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * fromIndex (inclusive) and toIndex (exclusive).
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException if fromIndex or toIndex are out of range
     * @throws IllegalArgumentException  if fromIndex > toIndex
     */
    @Override
    public MyLinkedList<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        MyLinkedList<E> subList = new MyLinkedList<>();
        Node<E> currentNode = getNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return subList;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Links the element as the last element of the list.
     *
     * @param element the element to be linked as the last element
     */
    private void linkLast(E element) {
        final Node<E> oldLastNode = last;
        final Node<E> newNode = new Node<>(element, oldLastNode, null);
        this.last = newNode;
        if (oldLastNode == null) {
            this.first = newNode;
        } else {
            oldLastNode.next = newNode;
        }
        this.size++;
    }

    /**
     * Unlinks the specified node from the list.
     *
     * @param current the node to unlink
     * @return the element previously contained in the unlinked node
     */
    private E unlink(Node<E> current) {
        final E element = current.data;
        final Node<E> next = current.next;
        final Node<E> prev = current.prev;

        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
            current.prev = null;
        }

        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            current.next = null;
        }

        current.data = null;
        this.size--;
        return element;
    }

    /**
     * Returns the node at the specified index.
     * The implementation optimizes traversal by checking whether the index
     * is in the first or second half of the list.
     *
     * @param index the index of the node to return
     * @return the node at the specified index
     */
    private Node<E> getNode(int index) {
        Node<E> nodeByIndex;
        if (index < (size >> 1)) {
            nodeByIndex = first;
            for (int i = 0; i < index; i++)
                nodeByIndex = nodeByIndex.next;
        } else {
            nodeByIndex = last;
            for (int i = size - 1; i > index; i--)
                nodeByIndex = nodeByIndex.prev;
        }
        return nodeByIndex;
    }

    /**
     * Checks if the given index is valid for element access.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * Checks if the given subList range is valid.
     *
     * @param fromIndex the start index of the subList
     * @param toIndex   the end index of the subList
     * @param size      the size of the list
     * @throws IndexOutOfBoundsException if fromIndex or toIndex are out of range
     * @throws IllegalArgumentException  if fromIndex > toIndex
     */
    private void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public NodeIterator iterator() {
        return new NodeIterator();
    }

    /**
     * A node in the doubly-linked list.
     * Each node contains the element data and references to both the previous
     * and next nodes in the list.
     *
     * @param <E> the type of element stored in the node
     */
    private static class Node<E> {
        /**
         * The data contained in this node.
         */
        private E data;

        /**
         * Reference to the next node in the list.
         */
        private Node<E> next;

        /**
         * Reference to the previous node in the list.
         */
        private Node<E> prev;

        /**
         * Constructs a new node with the given data and neighbor references.
         *
         * @param data the element to store in this node
         * @param prev reference to the previous node
         * @param next reference to the next node
         */
        Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Returns a string representation of this node.
         *
         * @return a string representation of this node
         */
        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    /**
     * An iterator over the elements in the linked list.
     * This iterator provides sequential access to the elements in the list
     * and supports the basic iteration operations in both directions.
     */
    public class NodeIterator {

        /**
         * The current node being examined.
         */
        private Node<E> current;

        /**
         * The next node to be returned by next().
         */
        private Node<E> next;

        /**
         * The previous node to be returned by previous().
         */
        private Node<E> previous;

        /**
         * The index of the next node to be returned.
         */
        private int nextIndex;

        /**
         * The index of the previous node to be returned.
         */
        private int previousIndex;

        /**
         * Constructs a new iterator starting at the beginning of the list.
         */
        NodeIterator() {
            this.next = first;
            this.nextIndex = 0;
            this.previousIndex = -1;
        }

        /**
         * Returns true if the iteration has more elements when traversing forward.
         *
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            return this.nextIndex < size;
        }

        /**
         * Returns true if the iteration has more elements when traversing backward.
         *
         * @return true if the iteration has more elements
         */
        public boolean hasPrevious() {
            return this.previousIndex >= 0;
        }

        /**
         * Returns the next element in the iteration (forward direction).
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.current = next;
            this.next = next.next;
            this.previous = current;
            this.nextIndex++;
            this.previousIndex++;
            return this.current.data;
        }

        /**
         * Returns the previous element in the iteration (backward direction).
         *
         * @return the previous element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            this.current = previous;
            this.previous = previous.prev;
            this.next = current;
            this.nextIndex--;
            this.previousIndex--;
            return this.current.data;
        }
    }
}