package Match.match230;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class match230 {


    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count =0;
        int index = 0;
        if(ruleKey.equals("type")){
            index = 0;
        }else if(ruleKey.equals("color")){
            index = 1;
        }else{
            index = 2;
        }
        for(List<String> list:items){
            if(list.get(index).equals(ruleValue) ){
                count++;
            }
        }
        return count;
    }


    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int ans = Integer.MAX_VALUE;
        int min  = Integer.MAX_VALUE;
        Arrays.sort(baseCosts);
        Arrays.sort(toppingCosts);
        for(int base:baseCosts){
            if(base==target) return target;
            if(base>target&& (base-target)>min) break;
            Set<Integer> set = new HashSet<>();
            set.add(base);
            for (int top:toppingCosts){
                Set<Integer> tempSet = new HashSet<>();
                for (int s:set){
                    int a = s+top;
                    int b = s+top*2;
                    min = Math.min(min,Math.abs(target-s));
                    min = Math.min(min,Math.abs(target-a));
                    min = Math.min(min,Math.abs(target-b));
//                    if(a>target&&a-target>min) continue;
//                    if(b>target&&b-target>min) continue;
//                    int t = Math.min(Math.abs(target-a),Math.abs(target-b));

                    tempSet.add(a);
                    tempSet.add(b);
                }
                set.addAll(tempSet);
            }
            if(min==0) return target;
            if(set.contains(target-min)){
                ans = target-min;
            }else if(ans!=target-min){
                ans = target+min;
            }
        }
        return ans;
    }

    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if(sum1>sum2){
            return minOperations(nums2,nums1);
        }
        int diff = sum2-sum1;
        int[] arr = new int[6];
        for (int i:nums1){
            arr[6-i]++;
        }
        for (int i:nums2){
            arr[i-1]++;
        }
        int count = 0;
        for (int i=5;i>=1&&diff>0;i--){
            while (arr[i]!=0&&diff>0){
                arr[i]--;
                count++;
                diff-=i;
            }
        }
        return diff>0 ? -1 : count;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6,6};
        int[] b = new int[]{1};
        System.out.println(new match230().minOperations(a,b));
    }
}
