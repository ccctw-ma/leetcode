package Medium.HeapTest;


import java.util.*;

/*
* 373. 查找和最小的K对数字
给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。

定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。

找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。

示例 1:

输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
输出: [1,2],[1,4],[1,6]
解释: 返回序列中的前 3 对数：
     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
示例 2:

输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
输出: [1,1],[1,1]
解释: 返回序列中的前 2 对数：
     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
示例 3:

输入: nums1 = [1,2], nums2 = [3], k = 3
输出: [1,3],[2,3]
解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/2  */


public class kSmallestPairs {

    class node{
        int x;
        int y;
        int sum;
        public node(int a,int b){
            this.x=a;
            this.y=b;
            this.sum=this.x+this.y;
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res=new ArrayList<>();
        if(nums1.length==0||nums2.length==0||k>nums1.length*nums2.length) return res;
        List<node> list=new ArrayList<>();
        for (int i:nums1){
            for (int j:nums2){
                list.add(new node(i,j));
            }
        }
        list.sort(Comparator.comparingInt(o -> o.sum));
        for (int i=0;i<k;i++){
            List<Integer> list1=new ArrayList<>();
            list1.add(list.get(i).x);
            list1.add(list.get(i).y);
            res.add(list1);
        }
        return res;
    }



    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k){
        List<List<Integer>> listList=new ArrayList<>();
        int a=nums1.length;
        int b=nums2.length;
        if(a==0||b==0) return listList;
        int[][] arr=new int[a][b];
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                arr[i][j]=nums1[i]+nums2[j];
            }
        }
        int min=arr[0][0];
        int max=arr[a-1][b-1];
        int tar;
        if(k>=a*b){
            tar=max;
        }else {
            while (min<max){
                tar=min+(max-min)/2;
                if(check(arr,k,tar)){
                    max=tar;
                }else {
                    min=tar+1;
                }
            }
            tar=min;
        }
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                int sum=arr[i][j];
                if(sum<=tar){
                    List<Integer> list=new ArrayList<>();
                    list.add(nums1[i]);
                    list.add(nums2[j]);
                    listList.add(list);
                }
            }
        }
        return listList;
    }

    public boolean check(int[][] arr,int k,int tar){
        int i=arr.length-1;
        int j=0;
        int count=0;
        while (i>=0&&j<arr[0].length){
            if(arr[i][j]<=tar){
                count+=i+1;
                j++;
            }else {
                i--;
            }
        }
        return count>=k;
    }

    public List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        List<List<Integer>> resList = new ArrayList<>();
        if (n1 == 0 || n2 == 0 || k == 0) {
            return resList;
        }
        // 优先队列，优先队列的排列标准是按照的和的升序来的，优先队列中存放的是下标
        Queue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> nums1[o[0]] + nums2[o[1]]));
        // 初始化优先队列
        for (int i = 0; i < n1; i++) {
            // 以第一个数组中的下标为对照，在第二个数组中间选取
            priorityQueue.offer(new int[]{i, 0});
        }
        while (priorityQueue.size() > 0 && k > 0) {
            int[] pair = priorityQueue.poll();
            if (pair[1] + 1 < n2) {
                // 这个优先级队列并不是一上来就全部建立好的，而是一边拿出最小的元素，一边添加进下一个可能的pair
                priorityQueue.offer(new int[]{pair[0], pair[1] + 1});
            }
            List<Integer> list = new ArrayList<>();
            list.add(nums1[pair[0]]);
            list.add(nums2[pair[1]]);
            resList.add(list);
            k--;
        }

        return resList;
    }



    public static void main(String[] args) {
        int[] n1=new int[]{-10,-4,0,0,6};
        int[] ints=new int[]{3,5,6,7,8,100};
//        System.out.println(new kSmallestPairs().kSmallestPairs2(n1,ints,10));

        PriorityQueue<Integer> queue=new PriorityQueue<>(((o1, o2) -> o2-o1));
        for (int i:ints) queue.add(i);
        System.out.println(queue.offer(1000)+" "+queue.peek());
        System.out.println(Arrays.toString(queue.toArray()));
    }


}
