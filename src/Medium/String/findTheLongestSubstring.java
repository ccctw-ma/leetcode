package Medium.String;


/**
 * 1371. 每个元音包含偶数次的最长子字符串
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 *
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 *
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/5/20  */

public class findTheLongestSubstring {

        //效率太低了
//    public int findTheLongestSubstring(String s) {
//        int max=0;
//        int[] chars=new int[26];
//        int[][] suffix=new int[s.length()+1][5];
//        for (int i=1;i<=s.length();i++){
//            chars[s.charAt(i-1)-'a']++;
//            suffix[i][0]=chars[0];
//            suffix[i][1]=chars[4];
//            suffix[i][2]=chars[8];
//            suffix[i][3]=chars[14];
//            suffix[i][4]=chars[20];
//            for (int j=0;j<i;j++){
//                int temp=(suffix[i][0]^suffix[j][0])|(suffix[i][1]^suffix[j][1])
//                        |(suffix[i][2]^suffix[j][2])|(suffix[i][3]^suffix[j][3])
//                        |(suffix[i][4]^suffix[j][4]);
//                if((temp&1)==0){
//                    max=Math.max(max,i-j);
//                    System.out.println(i+" "+j+" "+" "+max);
//                    break;
//                }
//            }
//        }
//        return max;
//    }

    public int findTheLongestSubstring(String s){

        //很巧妙
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new findTheLongestSubstring().findTheLongestSubstring("aeiouaa"));

    }
}
