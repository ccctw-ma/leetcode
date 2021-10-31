package Easy.StringTest;


/**
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 *
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：text = "nlaebolko"
 * 输出：1
 * 示例 2：
 *
 *
 *
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * 示例 3：
 *
 * 输入：text = "leetcode"
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 1189. “气球” 的最大数量 */

public class maxNumberOfBalloons {
    
    public int maxNumberOfBalloons(String text) {
        int[] bucket=new int[26];
        for (char c:text.toCharArray()){
            bucket[c-'a']++;
        }
        int sum=0;
        while (bucket[1]>0&&bucket[0]>0&&bucket[11]>1&&bucket[14]>1&&bucket[13]>0){
            bucket[1]--;
            bucket[0]--;
            bucket[11]-=2;
            bucket[14]-=2;
            bucket[13]--;
            sum++;
        }
        return sum;
    }
    
    public static void main(String[] args) {

    }
}
