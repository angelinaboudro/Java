/**
 * Angelina Boudro - 06/22/2019
 * LinkedList class implements a doubly-linked list.
 */
import java.util.ArrayList;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList() {
        doClear();
    }

    private void clear() {
        doClear();
    }


    /**
     * Change the size of this collection to zero.
     */
    public void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     *
     * @param x   any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */


    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Reverse Method
     */

    public void reverseMethod() {
        if (theSize < 2)
            return;

        Node<AnyType> p1, p2, p3;

        p1 = null;
        p2 = beginMarker;
        p3 = beginMarker.next;

        while (p3 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }

        p2.next = p1;

        endMarker = beginMarker;
        beginMarker = p2;
        {
        }
    }

    /**  End Reverse Method */


    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */

    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     *
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx   index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType x : this)
            sb.append(x + " ");
        sb.append("]");

        return new String(sb);
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    /**
     * Swap Method
     */
    public void swapMethod(int pos1, int pos2) {

        if ((pos1 < 0 || pos1 >= size()) || (pos2 < 0 || pos2 >= size()))
            throw new IndexOutOfBoundsException();

        Node<AnyType> node1 = getNode(pos1);
        Node<AnyType> node2 = getNode(pos2);

        Node<AnyType> temp1 = node1.prev;
        Node<AnyType> temp2 = node1.next;

        Node<AnyType> temp3 = node2.prev;
        Node<AnyType> temp4 = node2.next;

        if (temp1 != null)
            temp1.next = node2;

        node2.prev = temp1;

        if (temp2 != null)
            temp2.prev = node2;

        node2.next = temp2;

        if (temp3 != null)
            temp3.next = node1;

        node1.prev = temp3;

        if (temp4 != null)
            temp4.prev = node1;

        node1.next = temp4;

    }
    // End swap method

    /**
     * Erase Method
     */

    public void eraseMethod(int pos, int n) {
        if (n == 0)
            return;

        if (pos < 0 || pos >= size() || pos + n > size())
            throw new IndexOutOfBoundsException();

        Node<AnyType> currentNode = getNode(pos).prev;

        Node<AnyType> nextNode = currentNode;
        int count = 0;

        while (count <= n) {
            nextNode = nextNode.next;
            count++;
        }

        currentNode.next = nextNode;

        if (nextNode != null)
            nextNode.prev = currentNode;

        theSize -= n;


    }
    // End Erase Method


    /**
     * InsertList Method
     */
    public void insertListMethod(MyLinkedList<AnyType> list, int pos) {
        if (pos < 0 || pos >= size())
            throw new IndexOutOfBoundsException();

        for (int i = 0; i < list.size(); i++) {
            add(pos, list.get(i));
            pos++;
        }

    }
    // End InsertListMethod

    /**
     * Shift Method
     */
    public void shiftMethod(int s) {

        Node<AnyType> p = beginMarker.next;
        Node<AnyType> q = endMarker.prev;

//doubly linked

        q.next = p;
        p.prev = q;

//shift the list

        if (s > 0) {

            for (int i = 0; i < s; i++) {
                p = p.next;
                q = q.next;
            }

        } else {

            for (int i = 0; i > s; i--) {
                p = p.prev;
                q = q.prev;

            }
        }

        beginMarker.next = p;
        p.prev = beginMarker;
        endMarker.prev = q;
        q.next = endMarker;

    }


    // End ShiftMethod


    // itemCountMethod

    public int itemCountMethod(AnyType item) {

        int count = 0;


        for (int i = 0; i < size(); i++) {

            if (get(i).equals(item)) {


                count++;

            }

        }

        return count;

    }


    ArrayList<AnyType> sublist(int start, int end) {


        if (start < 0 || start >= size() || end < 0 || end >= size()

                || start > end) {

            return null; // invalid indices

        }


        ArrayList<AnyType> list = new ArrayList<AnyType>();


        for (int i = start; i <= end; i++) {

            list.add(get(i));

        }

        return list;

    }

    // given a list of variable arguments containing indices, returns the

    // elements corresponding to those indices

    ArrayList<AnyType> select(int... indices) {

        ArrayList<AnyType> list = new ArrayList<AnyType>();

        for (int i = 0; i < indices.length; i++) {

            int index = indices[i];

            if (index < 0 || index >= size()) {

                list.add(null);

            } else

                list.add(get(index));

        }

        return list;
    }
}

//End of itemCountMethod


// TEST METHOD //

    class TestLinkedList {
        public static void main(String[] args) {
            MyLinkedList<Integer> lst = new MyLinkedList<>();

            for (int i = 0; i < 10; i++)
                lst.add(i);
            for (int i = 20; i < 30; i++)
                lst.add(0, i);

            lst.remove(0);
            lst.remove(lst.size() - 1);

            System.out.println("\nThe count of # of times this item appears in the list: ");
            System.out.println(lst.itemCountMethod(1));

            System.out.println("\nThis is the SubList: ");
            System.out.println(lst.sublist(5, 15));

            System.out.println("\nThis is the SelectMethod: ");
            System.out.println(lst.select(3, 9));

            System.out.println("\nPrior to ReverseMethod: ");
            System.out.println(lst);
            lst.reverseMethod();
            System.out.println("\nImplemented ReverseMethod and return to original MyLinkedList: ");
            System.out.println(lst);
            lst.reverseMethod();

            System.out.println(lst);
            System.out.println("\nMyLinkedList: ");

            java.util.Iterator<Integer> itr = lst.iterator();
            while (itr.hasNext())
                System.out.print(itr.next() + " ");
            System.out.println("\nEnd");

            System.out.println("\nMyLinkedList");


            System.out.println("\rPrior to SwapMethod: ");
            System.out.println(lst);

            lst.swapMethod(0, lst.size() - 1);

            System.out.println("\nEnd SwapMethod: ");
            System.out.println(lst);


            lst.eraseMethod(0, 3);
            System.out.println("\nEraseMethod: ");
            System.out.println(lst);

            System.out.println("\nShiftMethod: ");
            System.out.println(lst);

            System.out.println("\nAfter ShiftMethod:");
            System.out.println(lst);

            lst.shiftMethod(2);

            System.out.println(lst);
            lst.shiftMethod(-1);

            MyLinkedList<Integer> list2 = new MyLinkedList<Integer>();
            list2.add(40);
            list2.add(50);
            list2.add(60);

            System.out.println("\nThe insertListMethod: ");
            System.out.println(list2);

            lst.insertListMethod(list2, 2);

            System.out.println("\nImplementation of the insertListMethod: ");
            System.out.println(lst);


            while (itr.hasNext()) {
                itr.next();
                itr.remove();
                System.out.println(lst);
            }
        }
    }





