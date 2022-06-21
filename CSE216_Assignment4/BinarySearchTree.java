
import java.util.*;


public class BinarySearchTree<T extends Comparable<T>> extends Thread implements Iterable<T>{
    private String name;
    private Node<T> ptr;
    private List<T> sorted;
    private int iter;

    public BinarySearchTree() {

    }

    public static void main(String... args) {
        // each tree has a name, provided to its constructor
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>("Oak");

        // adds the elements to t1 in the order 5, 3, 0, and then 9
        t1.addAll(Arrays.asList(5, 3, 0, 8));
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>("Maple");

        // adds the elements to t2 in the order 9, 5, and then 10
        t2.addAll(Arrays.asList(9, 7, 10));

        System.out.println(t1); // see the expected output for exact format
        t1.forEach(System.out::println); // iteration in increasing order

        System.out.println(t2); // see the expected output for exact format
        t2.forEach(System.out::println); // iteration in increasing order

        BinarySearchTree<String> t3 = new BinarySearchTree<>("Cornucopia");
        t3.addAll(Arrays.asList("coconut", "apple", "banana", "plum", "durian", "no durians on this tree!", "tamarind"));

        System.out.println(t3); // see the expected output for exact format
        t3.forEach(System.out::println); // iteration in increasing order

        List<BinarySearchTree<Integer>> bstlist = new ArrayList<>();
        bstlist.add(t1);
        bstlist.add(t2);
        merge(bstlist);
    }

    private void addAll(List<T> asList) {
        for (T t : asList) {
            insert(t);
        }
        List<T> copy;
        copy = asList;

        Collections.sort(copy);
        sorted = copy;
    }


    public void insert(T key){
        ptr = insertRec(ptr, key);
    }

    public Node<T> insertRec(Node<T> ptr, T key){
        if(ptr == null){
            ptr = new Node<>(key);
            return ptr;
        }
        int compare = ptr.val.compareTo(key);
        if (compare > 0)
            ptr.left = insertRec(ptr.left, key);
        else if (compare < 0)
            ptr.right = insertRec(ptr.right, key);

        return ptr;
    }


    public BinarySearchTree(String name){
        this.name = name;
        ptr = null;
        sorted = new ArrayList<>();
        iter = 0;
    }

    @Override
    public Iterator<T> iterator() {
        //use next method to create traversal of ordered list
        Iterator<T> newIterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iter != sorted.size();
            }

            @Override
            public T next() {
                return sorted.get(iter++);
            }

        };
        return newIterator;
    }


    @Override
    public String toString(){

        return "[" + name + "] " + toStringPreorder(ptr);
    }

    public String toStringPreorder(Node<T> ptr) {
        String s = "";
        if (ptr == null) {
            return "";
        }

        s += ptr.val.toString();
        if(ptr.left != null){
            s += " L: (" + toStringPreorder(ptr.left) + ") ";
        }
        if(ptr.right != null){
            s += " R: (" + toStringPreorder(ptr.right) + ") ";
        }
        return s;
    }

    public static <T extends Comparable<T>> List<T> merge(List<BinarySearchTree<T>> bstlist){
        List<T> bigList = new ArrayList<>();

        List<BinarySearchTree<T>> threadList = new ArrayList<>();

        for(int i = 0; i< bstlist.size(); i++){
            BinarySearchTree<T> thread = new BinarySearchTree<>();
            threadList.add(thread);
            thread.start();
        }
        ArrayList pointers = new ArrayList(bstlist.size());






        return bigList;

    }
    @Override
    public void run(){

    }


    class Node<T>{
        private final T val;
        private Node<T> left;
        private Node<T> right;

        Node(T t){this.val = t;}

        //take small lists(sorted) and merge into big one
        //global list
        //have a thread that deals with one list
        //use extend thread class also get lock of the big list
    }
}
