/**
 * The MyList interface represents an ordered collection of elements.
 * It provides basic operations for adding, removing, accessing, and manipulating elements
 * by their position in the list. Implementations of this interface may vary in their
 * internal structure (e.g., array-based or linked-list-based).
 *
 * @param <E> the type of elements in this list
 */
public interface MyList<E> {

    /**
     * Appends the specified element to the end of this list (optional operation).
     *
     * @param e element to be appended to this list
     * @return true if the element was added successfully, false otherwise
     *         (e.g., if the element is null and the implementation doesn't allow null elements)
     */
    boolean add(E e);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).
     *
     * @param e element to be removed from this list, if present
     * @return true if this list contained the specified element, false otherwise
     */
    boolean remove(E e);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param e element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     */
    E set(int index, E e);

    /**
     * Returns a view of the portion of this list between the specified
     * fromIndex, inclusive, and toIndex, exclusive.
     * The returned list is backed by this list, so changes in the returned list
     * are reflected in this list, and vice-versa.
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException if fromIndex or toIndex
     *         are out of range (fromIndex < 0 || toIndex > size())
     * @throws IllegalArgumentException if fromIndex > toIndex
     */
    MyList<E> subList(int fromIndex, int toIndex);

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();
}