package Match.match185;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class TreeSetIteratorTest {

    public static void main(String[] args) {
        TreeSet<String> treeSet=new TreeSet<>();
        treeSet.add("1");
        treeSet.add("5");
        treeSet.add("3");
        treeSet.add("4");
        treeSet.add("2");
        treeSet.add("9");
        for (String s : treeSet) {
//            System.out.println(s);
        }

//        for(Iterator iterator=treeSet.descendingIterator();iterator.hasNext();){
////            System.out.println(iterator.next());
//        }

//        System.out.println(treeSet.ceiling("3"));
//        System.out.println(treeSet.floor("8"));
//        System.out.println(treeSet.subSet("2",true,"8",true));
//        System.out.println(treeSet.headSet("9",true));
//        System.out.println(treeSet.tailSet("1",true));
//        System.out.println(treeSet.pollLast());
        PriorityQueue<Integer> integers=new PriorityQueue<>((o1, o2) -> o1-o2);
        integers.add(1);
        integers.add(2);
        integers.add(10);
        integers.add(5);
        integers.add(6);
        integers.add(3);
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
    }
}
