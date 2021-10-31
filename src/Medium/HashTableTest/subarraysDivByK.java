package Medium.HashTableTest;


/*
* 974. 和可被 K 整除的子数组
给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。



示例：

输入：A = [4,5,0,-2,-3,1], K = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 K = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]


提示：

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000*/

import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2020/5/15  */


public class subarraysDivByK {


    public int subarraysDivByK(int[] A, int K) {
        int count=0,len=A.length;
        if(len==0) return count;
        int min=A[0],max=A[0];
        for (int i=1;i<len;i++){
            A[i]+=A[i-1];
            max=Math.max(max,A[i]);
            min=Math.min(min,A[i]);
        }
        int[] map=new int[max-min+1];
        for (int a:A){
            if(a%K==0)  count++;
            for (int i=a-K;i>=min;i-=K) count+=map[i-min];
            for (int i=a+K;i<=max;i+=K) count+=map[i-min];
            count+=map[a-min];
            map[a-min]++;
        }
        return count;
    }

    public int subarraysDivByK2(int[] A, int K) {
        int count=0,len=A.length;
        if(len==0) return count;
        Map<Integer,Integer> map=new HashMap<>();
        A[0]=A[0]%K;
        if(A[0]<0) A[0]+=K;
        if(A[0]==0) count++;
        map.put(A[0],1);
        for (int i=1;i<len;i++){
            A[i]+=A[i-1];
            A[i]%=K;
            if(A[i]<0) A[i]+=K;
            if(A[i]==0) count++;
            count+=map.getOrDefault(A[i],0);
            map.put(A[i],map.getOrDefault(A[i],0)+1);
        }
        return count;
    }

    public int subarraysDivByK3(int[] A, int K) {
        int N = A.length;
        A[0] %= K;
        for (int i = 1; i < N; ++i)
            A[i] =(A[i] + A[i - 1]) % K;

        int[] count = new int[K];
        count[0]++;//这一步很妙，把A[i]==0的情况解决了
        for (int x: A)
            count[(x % K + K) % K]++;
        int ans = 0;
        for (int v: count)
            ans += v * (v - 1) / 2;
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new subarraysDivByK().subarraysDivByK2(new int[]{-1,2,9},2));
    }
}
