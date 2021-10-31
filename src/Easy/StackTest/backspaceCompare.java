package Easy.StackTest;


/**
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 *
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 *
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 *
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 **/


import java.util.Stack;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/11  844. 比较含退格的字符串 */
public class backspaceCompare {

    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> stacks=new Stack<>();
        Stack<Character> stackt=new Stack<>();
        for (int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(!stacks.isEmpty()&&ch=='#'){
                stacks.pop();
                continue;
            }
            if(ch!='#'){
                stacks.push(ch);
            }

        }
        for (int i=0;i<t.length();i++){
            char ch=t.charAt(i);
            if(!stackt.isEmpty()&&ch=='#'){
                stackt.pop();
                continue;
            }
            if(ch!='#'){
                stackt.push(ch);
            }


        }
        if(stacks.size()==stackt.size()){
            return stacks.equals(stackt);
        }else {
            return false;
        }
    }



    public boolean backspaceCompare2(String s, String t) {
        int skip_s=0,skip_t=0;
        int i=s.length()-1;
        int j=t.length()-1;
        while (i>=0&&j>=0){
            while (i>=0){
                if(s.charAt(i)=='#'){
                    skip_s++;
                    i--;
                }else if(skip_s>0){
                    while (skip_s>0&&i>=0&&s.charAt(i)!='#'){
                        skip_s--;
                        i--;
                    }
                }else{
                    break;
                }

            }

            while (j>=0){
                if(t.charAt(j)=='#'){
                    skip_t++;
                    j--;
                }else if(skip_t>0){
                    while (skip_t>0&&j>=0&&t.charAt(j)!='#'){
                        skip_t--;
                        j--;
                    }
                }else {
                    break;
                }
            }

            if(i<0&&j<0){
                break;
            } else if (i<0||j<0) {
                return false;
            }

            if(s.charAt(i)==t.charAt(j)){
                i--;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * public boolean backspaceCompare(String S, String T) {
     *         int i = S.length() - 1, j = T.length() - 1;
     *         int skipS = 0, skipT = 0;
     *
     *         while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
     *             while (i >= 0) { // Find position of next possible char in build(S)
     *                 if (S.charAt(i) == '#') {skipS++; i--;}
     *                 else if (skipS > 0) {skipS--; i--;}
     *                 else break;
     *             }
     *             while (j >= 0) { // Find position of next possible char in build(T)
     *                 if (T.charAt(j) == '#') {skipT++; j--;}
     *                 else if (skipT > 0) {skipT--; j--;}
     *                 else break;
     *             }
     *             // If two actual characters are different
     *             if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
     *                 return false;
     *             // If expecting to compare char vs nothing
     *             if ((i >= 0) != (j >= 0))
     *                 return false;
     *             i--; j--;
     *         }
     *         return true;
     *     }
*/
    public static void main(String[] args) {
        System.out.println(new backspaceCompare().backspaceCompare2("bxj##tw","bxo#j##tw"));
    }
}
