package Easy.ArrayTest;



/**
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 *
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 *
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 *
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 *  
 *
 * 示例：
 *
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 **/

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/4 1128. 等价多米诺骨牌对的数量 */

public class numEquivDominoPairs {

    public int numEquivDominoPairs(int[][] dominoes) {
        Arrays.sort(dominoes, Comparator.comparingInt(o -> o[0]));
        int sum=0;
        for (int i=0;i<dominoes.length;i++){
            int a=dominoes[i][0];
            int b=dominoes[i][1];
            int j=i+1;
            while (j<dominoes.length&&dominoes[j][0]<Math.max(a,b)){
                if((a==dominoes[j][0]&&b==dominoes[j][1])||(a==dominoes[j][1]&&b==dominoes[j][0])){
                    sum++;
                }
                j++;
            }
        }
        return sum;
    }

    //妙呀，因为每个元素1~9,用一个100的桶记录一个和
    public int numEquivDominoPairs2(int[][] dominoes) {
        int ans = 0;
        int[] cp = new int[100];
        for(int[] arr:dominoes){
            Arrays.sort(arr);
            ans+=cp[arr[0]*10+arr[1]]++;
        }
        return ans;
    }

    //妙呀
    public int numEquivDominoPairs3(int[][] dominoes) {
        //由于数值固定,使用邻接矩阵村数据
        int[][] linjie=new int[10][10];
        for (int i = 0; i < dominoes.length; i++) {
            linjie[dominoes[i][0]][dominoes[i][1]]++;
        }
        int count=0;
        for (int i = 0; i <10; i++) {
            count+=getCombinationAll(linjie[i][i]);
            for (int j = i+1; j < 10; j++) {
                count+=getCombinationAll(linjie[i][j]+linjie[j][i]);
            }
        }
        return count;
    }
    //全排列 A
    private int getCombinationAll(int compareCount) {
        if (compareCount<=1)return 0;
        if(compareCount==2)return 1;
        return compareCount*(compareCount-1) / 2;
    }

    public static void main(String[] args) {

    }
}
