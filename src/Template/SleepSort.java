package sort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SleepSort {



    public static int add(int a,int b){
        if(b==0) return a;
        int sum=a^b;
        int carry=(a&b)<<1;
        return add(sum,carry);
    }

    private void sleepSort(){
        int[] nums = new int[]{3, 5, 6, 7, 8, 1, 2, 4, 9};
        final int[] index = {0};

        for (int i: nums) {
            new Thread(() -> {
                try {
                    Thread.sleep(i * 1000);

                    nums[index[0]++] = i;
                    if (nums.length == index[0]) {
                        System.out.println(Arrays.toString(nums));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static double limitation(int n){
        double[] dp=new double[n];
        dp[0]=Math.PI*2/3;
        for (int i=1;i<n;i++){
            dp[i]=Math.cos(dp[i-1]);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
//        for (int i=10;i<10000;i+=1){
//            System.out.println(limitation(i));
//        }

        int[] arr=new int[]{1,2,3,4,5,6,7,8,9,10};
        Stream<Integer> res= Arrays.stream(arr)
                .boxed()
                .sorted(((o1, o2) -> o2-o1))
                .map(integer -> integer+1)
                .filter(integer -> integer % 2 == 0);
        for (Integer i:res.collect(Collectors.toList())){
            System.out.println(i);
        }

    }
}
