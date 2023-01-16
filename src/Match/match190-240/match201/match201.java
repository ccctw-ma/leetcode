package Match.match201;

import java.util.*;

public class match201 {

    public String makeGood(String s) {
        Stack<Character> stack=new Stack<>();
        for (char c:s.toCharArray()){
            if(stack.isEmpty()){
                stack.push(c);
            }else {
                char ch=stack.peek();
                if(Math.abs(ch-c)==32){
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }
        }
        StringBuilder builder=new StringBuilder();
        while (!stack.isEmpty()){
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

    public int maxNonOverlapping(int[] nums, int target) {
        int n=nums.length;
        int[] sums=new int[n+1];
        Map<Integer, Integer> map=new HashMap<>();
        map.put(0,0);
        List<int[]> arr=new ArrayList<>();
        for (int i=1;i<=n;i++){
            sums[i]=sums[i-1]+nums[i-1];
            if(map.containsKey(sums[i]-target)){
                int[] arrs=new int[]{map.get(sums[i]-target),i};
                arr.add(arrs);
            }
            map.put(sums[i],i);
        }
        arr.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        max=0;
        int count=0,right=0;
        for (int[] a:arr){
            if(a[0]>=right){
                count++;
                right=a[1];
            }
        }
        return count;
    }

    private int max;
    private void traverse(List<int[]> list,int index,int right,int count){
        max=Math.max(count,max);
        for (int i=index;i<list.size();i++){
            if(count+list.size()-index<max) return;
            int[] temp=list.get(i);
            if(temp[0]>=right){
                traverse(list,i+1,temp[1],count+1);
            }
        }
    }

    public char findKthBit(int n, int k) {
        StringBuilder builder=new StringBuilder();
        builder.append('0');
        for (int i=1;i<n;i++){
            StringBuilder temp=new StringBuilder(builder);
            builder.append('1').append(reverse(temp));
        }
        return builder.charAt(k-1);
    }

    private StringBuilder reverse(StringBuilder builder){
        for (int i=0;i<builder.length();i++){
            char c=builder.charAt(i);
            if (c == '0') {
                builder.setCharAt(i,'1');
            }else {
                builder.setCharAt(i,'0');
            }
        }
        return builder.reverse();
    }

    public static void main(String[] args) {
        System.out.println(new match201().maxNonOverlapping(new int[]{18,11,0,30,3,20,8,-6,0,0,0,-9,29,4,10,6,17,24,7,27,-9,11,8,5,6,21,0,27,4,24,-5,2,-2,0,12,7,9,14,26,6,-5,-1,-4,8,7,-3,2,4,-8,8,2,17,12,23,24,18,20,10,4,21,18,21,-8,2,9,5,-5,9,9,22,-8,-6,-3,20,-2,30,-9,7,-7,10,16,0,29,11,4,11,-8,13,18,-5,20,27,-8,22,15,-2,-8,8,22,0,28,11,29,26,15,7,-2,26,-2,16,-2,7,2,23,-3,30,21,6,24,20,20,28,8,30,16,3,15,-5,1,23,-6,-5,19,-2,-2,9,30,17,26,1,8,15,25,6,18,9,-5,2,23,21,17,15,21,13,-2,10,19,23,0,-3,5,25,0,-8,0,-2,-2,20,-4,29,-7,2,26,30,4,5,30,16,-1,0,24,18,13,3,19,20,-6,26,24,0,22,23,-8,28,7,16,27,23,20,18,10,-3,2,15,22,26,-4,15,29,19,17,28,-9,-2,27,22,10,19,30,0,15,27,-6,-5,15,-3,-9,14,-4,-6,24,-3,4,17,0,25,3,4,9,26,-8,21,7,30,18,24,2,-4,14,14,26,-1,19,18,25,-6,16,19,5,12,25,-10,9,3,21,30,-10,-4,3,18,14,7,-10,3,25,23,26,9,12,-9,30,-10,12,10,22,12,0,8,0,26,-1,13,17,16,2,18,3,12,12,23,23,-4,19,-7,14,17,2,4,30,10,0,7,-7,18,5,15,5,16,17,22,-9,29,8,28,5,11,8,14,-5,14,1,16,25,4,5,-3,2,20,-2,0,5,27,5,22,3,6,12,-8,26,-10,20,8,23,2,19,-4,9,19,-9,27,-9,8,25,8,21,29,20,19,-1,14,-1,10,8,8,24,30,15,5,30,16,25,-4,19,10,5,22,17,18,21,3,-4,11,25,10,15,15,26,10,-6,-5,-9,5,15,0,28,20,11,22,18,25,21,6,20,18,11,6,-1,-6,10,29,15,7,19,1,28,22,13,16,14,27,7,18,20,-7,27,26,7,1,11,14,18,-3,27,13,10,26,12,-4,0,-5,19,12,28,4,30,30,6,11,22,11,20,4,16,-7,8,27,11,-10,-8,24,0,-1,30,28,14,0,-6,-6,-5,12,13,11,-10,25,-1,-4,13,5,5,-7,3,25,-3,27,14,-4,15,-3,28,6,27,5,8,28,-4,13,9,14,-2,-6,-7,11,18,-6,3,-3,3,30,22,17,5,9,3,23,27,-9,13,28,6,-4,6,26,24,14,4,-8,22,-3,26,10,-2,13,-2,11,13,-9,17,18,15,-10,15,18,-10,5,13,11,-10,12,15,20,7,10,-10,13,27,-10,5,10,19,22,-7,-5,4,12,9,14,17,-7,-9,26,9,29,21,19,30,-8,-10,21,17,-7,16,-3,20,27,-5,20,-9,11,20,28,15,18,18,8,26,12,22,16,-3,16,3,17,-7,18,26,-4,25,5,-3,14,-2,14,27,-9,13,8,15,20,27,23,-9,21,14,29,-8,19,-4,-1,3,14,24,15,9,23,4,12,11,7,8,19,19,4,11,29,28,23,-1,30,15,19,2,26,-4,9,12,-4,8,10,25,3,21,11,14,20,26,26,14,13,25,-5,8,30,18,-8,29,-3,14,-10,20,20,3,7,-6,-6,12,28,24,21,1,-9,22,-6,-8,-1,9,6,13,8,-9,21,3,1,9,-7,-1,20,9,3,30,20,21},28));
    }
}
