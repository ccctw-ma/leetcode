package Easy.BitOperationTest;


/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例:
 *
 * 输入：
 * s = "abcd"
 * t = "abcde"
 *
 * 输出：
 * e
 *
 * 解释：
 * 'e' 是那个被添加的字母。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/17 389. 找不同 */
public class findTheDifference {

    public static char findTheDifference(String s, String t) {
        int index=0;
        for(char c:s.toCharArray()){
            index^=c;
        }
        for(char c:t.toCharArray()){
            index^=c;
        }
        return (char)index;
    }

    public static char findTheDifference2(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        char res = tt[tt.length - 1];
        for(int i=0; i<ss.length; i++){
            res += tt[i] - ss[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findTheDifference("abceddd","abcddedd"));
    }
}
