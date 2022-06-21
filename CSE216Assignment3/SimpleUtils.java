import java.util.*;
import java.util.stream.Collectors;

public class SimpleUtils {
    /**
     * Find and return the least element from a collection of given elements that are comparable.
     *
     * @param items:      the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     *                    If <code>true</code>, the element encountered earlier in the
     *                    iteration is returned, otherwise the later element is returned.
     * @param <T>:        the type parameter of the collection (i.e., the items are all of type T).
     * @return the least element in <code>items</code>, where ties are
     * broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        if(from_start){
           return items.stream().reduce((item1, item2) -> item1.compareTo(item2) < 0 ? item1:item2).orElse(null);
        }
        else{
            return items.stream().reduce((item1,item2)-> item2.compareTo(item1) < 0 ? item2:item1).orElse(null);
        }
    }

    /**
     * Flattens a map to a sequence of <code>String</code>s, where each element in the list is formatted
     * as "key -> value" (i.e., each key-value pair is converted to a string in this specific format).
     *
     * @param aMap the specified input map.
     * @param <K> the type parameter of keys in <code>aMap</code>.
     * @param <V> the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap){
        return aMap.entrySet().stream().map(key -> key.getKey()+" -> "+key.getValue()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> str = new ArrayList<String>();
        str.add("a");
        str.add("aa");
        str.add("aaa");
        str.add("bbb");
        str.add("ccc");
        str.add("dd");
        str.add("e");


        List<Integer> y = new ArrayList<Integer>();
        y.add(12);
        y.add(23765);
        y.add(8);
        y.add(19861);
        y.add(970);
        y.add(19091091);
        y.add(8);

        str.add("blueberry");
        System.out.println(least(y, false));

        System.out.println();
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        
        System.out.println((flatten(hm)));
        System.out.println();
        System.out.println((flatten(hm)).get(0));
        System.out.println();
        System.out.println((flatten(hm)).get(0).substring(1, 2));
        System.out.println();
        System.out.println((flatten(hm)).get(0).substring(3, 4));
        System.out.println();
        System.out.println((flatten(hm)).get(0).length());
    }
}
