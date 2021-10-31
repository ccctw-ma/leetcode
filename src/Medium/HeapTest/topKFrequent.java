package Medium.HeapTest;

import java.util.*;

public class topKFrequent {


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        List<node> list=new ArrayList<>();
        for (int i:map.keySet()){
            list.add(new node(i,map.get(i)));
        }
        list.sort((o1, o2) ->o2.count-o1.count);
        int[] arr=new int[k];
        for (int i=0;i<k;i++){
            arr[i]=list.get(i).val;
        }
        return arr;



    }

    class node{
        int val;
        int count;
        public node(int a,int b){
            this.val=a;
            this.count=b;
        }
    }


    public static void main(String[] args) {

        TreeSet<Integer> set=new TreeSet<>();
        set.add(3);
        set.add(7);
        set.add(9);
        set.add(1);
        set.add(2);
        System.out.println(set.descendingIterator().next());
    }
}
