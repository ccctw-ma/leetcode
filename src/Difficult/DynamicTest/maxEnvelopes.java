package Difficult.DynamicTest;


/*
* 354. 俄罗斯套娃信封问题
给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

说明:
不允许旋转信封。

示例:

输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出: 3
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2021/3/4  */


public class maxEnvelopes {


    /**
     * @apiNote can pass, but a bit slow*/
    public int maxEnvelopes2(int[][] envelopes) {
        int n = envelopes.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int max = 1;
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i=0;i<n;i++){
            for (int j=0;j<=i-1;j++){
                if(envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();

    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }




    public static void main(String[] args) {
        int[][] arr = new int[][]{{5,4},{6,7},{6,4},{2,3}};
        System.out.println(new maxEnvelopes().maxEnvelopes(arr));
    }
}
