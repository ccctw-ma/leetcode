package Medium.ArrayTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class findDiagonalOrder {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {


        TreeMap<Integer, Stack<Integer>> map=new TreeMap<>();
        for(int i=0;i<nums.size();i++){
            List<Integer> list=nums.get(i);
            for(int j=0;j<list.size();j++){
                int temp=i+j;
                Stack<Integer> stack=map.containsKey(temp)?map.get(temp):new Stack<>();
                stack.add(list.get(j));
                map.put(temp,stack);
            }
        }
        List<Integer> res=new ArrayList<>();
        for (int i:map.keySet()){
            Stack<Integer> stack=map.get(i);
            while (!stack.isEmpty()){
                res.add(stack.pop());
            }
        }
        int[] arr=new int[res.size()];
        int i=0;
        for (int t:res){
            arr[i++]=t;
        }
        return arr;
    }

    //     List<int[]> list = new ArrayList<>();

    //     for (int i = 0; i < nums.size(); i++){
    //         for (int j = 0; j < nums.get(i).size(); j++){
    //             list.add(new int[]{i, j});
    //         }
    //     }

    //    list.sort((o1, o2) -> {
    //         if (o1[0] + o1[1] - o2[0] - o2[1] != 0){
    //             return o1[0] + o1[1] - o2[0] - o2[1];
    //         } else {
    //             return o2[0] - o1[0];
    //         }
    //     });

    //     int[] arr = new int[list.size()];
    //     for (int i = 0; i < list.size(); i++){
    //         arr[i] = nums.get(list.get(i)[0]).get(list.get(i)[1]);
    //     }

    //     return arr;
}
