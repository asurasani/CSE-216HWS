import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BijectionGroup{

    public static void main(String... args){
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        Set<UnaryOperator<Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        // you have to figure out the data types in the lines below
        Group<UnaryOperator<Integer>> g = bijectionGroup(a_few);
        UnaryOperator<Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        UnaryOperator<Integer> f2 = g.inverseOf(f1);
        UnaryOperator<Integer> id = g.identity();

        System.out.println();
        for(Integer elem: a_few){
            System.out.printf("%d --> %d; ", elem, f1.apply(elem));
        }
        System.out.println();
        for(Integer elem: a_few){
            System.out.printf("%d --> %d; ", elem, f2.apply(elem));
        }
        System.out.println();
        for(Integer elem: a_few){
            System.out.printf("%d --> %d; ", elem, id.apply(elem));
        }
    }

    private static <T> Set<UnaryOperator<T>> bijectionsOf(Set<T> domain) {
        ArrayList<T> domain_arrList = new ArrayList<>(domain);

        Set<UnaryOperator<T>> new_array = new HashSet<>();
        ArrayList<ArrayList<T>> arrs = new ArrayList<>();

        boo_recursion(domain_arrList, domain_arrList.size(), arrs);

        for(ArrayList<T> arr: arrs){
            new_array.add(f -> arr.get(domain_arrList.indexOf(f)));
        }
        return new_array;
    }

    private static <T> void boo_recursion(ArrayList<T> array, int size, ArrayList<ArrayList<T>> arrs){
        if(size==1){
            add_method(array, arrs);
        }
        else{
            for(int i = 0; i< size; i++){
                boo_recursion(array, size-1, arrs);
                if(size % 2 == 1){
                    Collections.swap(array, i, size-1);
                }
                else{
                    Collections.swap(array, 0, size-1);
                }
            }
        }
    }
    private static <T> void add_method(ArrayList<T> array, ArrayList<ArrayList<T>> arrs){
        arrs.add((ArrayList) array.clone());
    }

    public static <T> Group<UnaryOperator<T>> bijectionGroup(Set<T> a_few){
        //Group<Bijection<T>> group = new Group<Bijection<T>>()
        Group<UnaryOperator<T>> group = new Group<UnaryOperator<T>>(){

            Set<UnaryOperator<T>> bijection_set = bijectionsOf(a_few);

            public UnaryOperator<T> binaryOperation(UnaryOperator<T> one, UnaryOperator<T> other) {
                //return a new function that's f(g(x))
                //return x -> other.apply(one.apply(x))
                return (UnaryOperator<T>) (other.andThen(one));
            }


            public UnaryOperator<T> identity() {
                for(UnaryOperator<T> a: bijection_set){
                    if(a_few.stream().allMatch(x -> a.apply(x).equals(x))){
                        return a;
                    }
                }
                return null;
            };


            public UnaryOperator<T> inverseOf(UnaryOperator<T> bijection) {
                for(UnaryOperator<T> a: bijection_set){
                    if(a_few.stream().allMatch(x -> a.apply(bijection.apply(x)).equals(x))){
                        return a;
                    }
                }
                return bijection;
            }
        };
        return group;
    }
}
