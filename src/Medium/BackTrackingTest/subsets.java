package Medium.BackTrackingTest;

import java.util.ArrayList;
import java.util.List;

public class subsets {


    public List<List<Integer>> subsets(int[] nums) {

        int n=nums.length;
        int size=(int) Math.pow(2,n) -1;
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<=size;i++){
            List<Integer> list=new ArrayList<>();
            int index=0,temp=i;
            while (temp!=0){
                if((temp&1)==1){
                    list.add(nums[index]);
                }
                temp>>=1;
                index++;
            }
            res.add(list);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new subsets().subsets(new int[]{1,2,3}));


    }
}
