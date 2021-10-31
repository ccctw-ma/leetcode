package Medium.DynamicTest;



/**
 * 838. 推多米诺
 * 一行中有 N 张多米诺骨牌，我们将每张多米诺骨牌垂直竖立。
 *
 * 在开始时，我们同时把一些多米诺骨牌向左或向右推。
 *
 *
 *
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。
 *
 * 同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *
 * 如果同时有多米诺骨牌落在一张垂直竖立的多米诺骨牌的两边，由于受力平衡， 该骨牌仍然保持不变。
 *
 * 就这个问题而言，我们会认为正在下降的多米诺骨牌不会对其它正在下降或已经下降的多米诺骨牌施加额外的力。
 *
 * 给定表示初始状态的字符串 "S" 。如果第 i 张多米诺骨牌被推向左边，则 S[i] = 'L'；如果第 i 张多米诺骨牌被推向右边，则 S[i] = 'R'；如果第 i 张多米诺骨牌没有被推动，则 S[i] = '.'。
 *
 * 返回表示最终状态的字符串。
 *
 * 示例 1：
 *
 * 输入：".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * 示例 2：
 *
 * 输入："RR.L"
 * 输出："RR.L"
 * 说明：第一张多米诺骨牌没有给第二张施加额外的力。
 * 提示：
 *
 * 0 <= N <= 10^5
 * 表示多米诺骨牌状态的字符串只含有 'L'，'R'; 以及 '.';*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/22  */

public class pushDominoes {


    public String pushDominoes(String dominoes) {
        if(dominoes.length()<=1) return dominoes;
        int len=dominoes.length();
        char[] chars=dominoes.toCharArray();
        boolean over=false;
        while (!over){
            int i=0;
            over=true;
            char[] dp=Arrays.copyOf(chars,len);
            while (i<len){
                while (i<len&&chars[i]!='.') i++;
                if(i>0&&i<len-1){
                    if(chars[i+1]=='L'&&(chars[i-1]=='L'||chars[i-1]=='.')){
                        dp[i]='L';
                        over=false;
                    }else if(chars[i-1]=='R'&&(chars[i+1]=='R'||chars[i+1]=='.')){
                        dp[i]='R';
                        over=false;
                    }
                }else if(i==0&&chars[i+1]=='L'){
                    dp[i]='L';
                    over=false;
                }else if(i==len-1&&chars[i-1]=='R'){
                    dp[i]='R';
                    over=false;
                }
                i++;
            }
            chars=Arrays.copyOf(dp,len);
        }
        return String.valueOf(chars);
    }


    //计算受力情况来进行判断，只需遍历两遍即可
    public String pushDominoes2(String S) {
        char[] A = S.toCharArray();
        int N = A.length;
        int[] forces = new int[N];

        // Populate forces going from left to right
        int force = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] == 'R') force = N;
            else if (A[i] == 'L') force = 0;
            else force = Math.max(force - 1, 0);
            forces[i] += force;
        }

        // Populate forces going from right to left
        force = 0;
        for (int i = N-1; i >= 0; --i) {
            if (A[i] == 'L') force = N;
            else if (A[i] == 'R') force = 0;
            else force = Math.max(force - 1, 0);
            forces[i] -= force;
        }

        StringBuilder ans = new StringBuilder();
        for (int f: forces)
            ans.append(f > 0 ? 'R' : f < 0 ? 'L' : '.');
        return ans.toString();
    }



    public static void main(String[] args) {
        System.out.println(new pushDominoes().pushDominoes(".......L.R...."));
    }
}
