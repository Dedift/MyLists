import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>(4);

        for (int i = 15; i > 0; i--) {
            list.add(i);
        }
        System.out.println("All added elements: " + list);

        System.out.println("List size: " + list.size());

        System.out.println("Element by index 5: " + list.get(5));

        list.set(5, 44);
        System.out.println("Element by index 5 after set: " + list.get(5));

        list.remove(7);
        System.out.println("List after remove element with index 7: " + list);

        MyArrayList<Integer> sublist = list.subList(5, 12);
        System.out.println("All elements in sublist: " + list);

        System.out.println("----------------------------------------------------------");

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 15; i++) {
            linkedList.add(i);
        }

        System.out.println("Linked list size: " + linkedList.size());

        MyLinkedList<Integer>.NodeIterator iterator = linkedList.iterator();
        System.out.print("All added elements: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();

        linkedList.remove(6);
        iterator = linkedList.iterator();
        System.out.print("All elements after remove 6: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();

        System.out.println("Element with index 3: " + linkedList.get(3));

        linkedList.set(3, 44);
        System.out.println("Element by index 3 after set: " + linkedList.get(3));

        MyLinkedList<Integer> linkedSublist = linkedList.subList(5, 12);
        iterator = linkedSublist.iterator();
        System.out.print("All elements in linkedSublist: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
