public interface MyList<E> {

    boolean add(E e);

    boolean remove(E e);

    E get(int index);

    E set(int index, E e);

    MyList<E> subList(int fromIndex, int toIndex);

    int size();
}