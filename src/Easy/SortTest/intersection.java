package Easy.SortTest;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 **/

import java.util.*;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/16 349. 两个数组的交集
 * @implNote 学会使用hashset*/
public class intersection {

    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        if(nums1.length<=nums2.length){
            Map<Integer,Integer> map=new HashMap<>();
            for (int i=0;i<nums1.length;i++){
                map.put(nums1[i],i);
            }
            for (int i=0;i<nums2.length;i++){
                if(map.containsKey(nums2[i])&&!list.contains(nums2[i])){
                    list.add(nums2[i]);
                }
            }
        }else {
            Map<Integer,Integer> map=new HashMap<>();
            for (int i=0;i<nums2.length;i++){
                map.put(nums2[i],i);
            }
            for (int i=0;i<nums1.length;i++){
                if(map.containsKey(nums1[i])&&!list.contains(nums1[i])){
                    list.add(nums1[i]);
                }
            }
        }
        int[] a=new int[list.size()];
        int i=0;
        for (Integer integer:list){
            a[i++]=integer;
        }
        return a;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }


    public static void main(String[] args) {
        int[] nums1 = {4,9,5,5,5,5,7,8};
        int[] nums2 = {9,4,9,8,4,2,3,4,5};
        for (int i:intersection(nums1,nums2)){
            System.out.println(i);
        }
    }


}

