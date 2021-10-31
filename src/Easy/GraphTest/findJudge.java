package Easy.GraphTest;



/**
 * 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
 *
 * 如果小镇的法官真的存在，那么：
 *
 * 小镇的法官不相信任何人。
 * 每个人（除了小镇法官外）都信任小镇的法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
 *
 * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：N = 2, trust = [[1,2]]
 * 输出：2
 * 示例 2：
 *
 * 输入：N = 3, trust = [[1,3],[2,3]]
 * 输出：3
 * 示例 3：
 *
 * 输入：N = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 * 示例 4：
 *
 * 输入：N = 3, trust = [[1,2],[2,3]]
 * 输出：-1
 * 示例 5：
 *
 * 输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * 输出：3
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/25 997. 找到小镇的法官 */

public class findJudge {

    public int findJudge(int N, int[][] trust) {
        if (trust.length==0){
            return N;
        }
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<trust.length;i++){
            int a=trust[i][0];
            int b=trust[i][1];
            map.put(a,map.getOrDefault(a,0)-1);
            map.put(b,map.getOrDefault(b,0)+1);
        }
        if(map.isEmpty()){
            return -1;
        }else {
            int temp;
            for (Integer integer:map.keySet()){
                temp=integer;
                if(map.get(temp)==N-1){
                    return temp;
                }
            }
        }
        return -1;
    }

    public int findJudge2(int N, int[][] trust) {
        if(trust.length == 0){
            return 1;
        }
        if(trust.length == 1){
            return trust[0][1];
        }
        int[] num = new int[N+1];
        for (int[] ints : trust) {
            num[ints[0]]--;
            num[ints[1]]++;
        }
        for(int i=1 ; i<= N;i++){
            if(num[i] == N-1){
                return i;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int[][] array=new int[][]{{1,2},{3,2},{1,3},{4,5},{5,2},{1,4},{2,3},{4,3},{5,1},{2,5},{4,1},{5,3}};
        System.out.println(new findJudge().findJudge2(5,array));
    }
}
