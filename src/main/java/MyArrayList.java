import java.util.Arrays;

/**
 * A resizable array implementation of the MyList interface.
 * This is a simplified version of the ArrayList class, providing basic list operations.
 *
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * The default initial capacity of the list.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the list are stored.
     * The capacity of the list is the length of this array buffer.
     */
    private Object[] elements;

    /**
     * The number of elements in this list.
     */
    private int size;

    /**
     * Constructs an empty list with the default initial capacity (10).
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

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
        checkCapacity(size + 1);
        elements[size++] = element;
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
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    fastRemove(i);
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
     * @throws IndexOutOfBoundsException if the index is out of range (index >= size)
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index >= size)
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * fromIndex, inclusive, and toIndex, exclusive.
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException if fromIndex or toIndex are out of range
     * @throws IllegalArgumentException if fromIndex > toIndex
     */
    @Override
    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        MyArrayList<E> subList = new MyArrayList<>(toIndex - fromIndex);
        System.arraycopy(elements, fromIndex, subList.elements, 0, toIndex - fromIndex);
        subList.size = toIndex - fromIndex;
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
     * Returns a string representation of this list.
     * The string representation consists of a list of the list's elements
     * enclosed in square brackets ("[]").
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        return "MyArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    /**
     * Ensures that the list has sufficient capacity for the minimum capacity requested.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void checkCapacity(int minCapacity) {
        if (elements == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        if (minCapacity - elements.length > 0)
            grow(minCapacity);
    }

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Private remove method that skips bounds checking and does not return the value removed.
     *
     * @param index the index of the element to be removed
     */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    /**
     * Checks if the given index is in range. If not, throws an appropriate runtime exception.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * Checks if the given subList range is valid.
     *
     * @param fromIndex the start index of the subList
     * @param toIndex the end index of the subList
     * @param size the size of the list
     * @throws IndexOutOfBoundsException if fromIndex or toIndex are out of range
     * @throws IllegalArgumentException if fromIndex > toIndex
     */
    private void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
    }
}