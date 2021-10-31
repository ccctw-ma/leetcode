package Medium.ArrayTest;


import java.util.ArrayList;
import java.util.List;



/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/9  */

public class generateParenthesis {

    private List<String> strings;
    private int n;
    public List<String> generateParenthesis(int n) {
        this.strings=new ArrayList<>();
        this.n=n;
        StringBuilder builder=new StringBuilder();
        builder.append('(');
        trackBack(builder,1,0);
        return strings;
    }

    private void trackBack(StringBuilder builder,int left,int right){
        StringBuilder temp=new StringBuilder(builder);
        if(left==n&&right==n){
            strings.add(temp.toString());
        } else if(left<=n&&right<=left){
            temp.append('(');
            trackBack(temp,left+1,right);
            temp.deleteCharAt(left+right);
            temp.append(')');
            trackBack(temp,left,right+1);
        }
    }



    //使用char[]进行运算，可以更快一些
    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<String>();
        if (n < 0) return list;
        dfs(0, n, n, new char[n << 1], list);
        return list;
    }

    private void dfs(int idx, int leftRemain, int rightRemain, char[] string, List<String> list) {
        if (idx == string.length) {
            list.add(new String(string));
            return;
        }

        // 枚举这一层所有可能的选择
        // 选择一种可能之后，进入下一层搜索

        // 什么情况可以选择左括号？左括号的数量 > 0
        // 选择左括号，然后进入下一层搜索
        if (leftRemain > 0) {
            string[idx] = '(';
            dfs(idx+1, leftRemain-1, rightRemain, string, list);
        }

        // 当左括号、右括号的数量一样时，只能选择左括号
        // 什么情况可以选择右括号？(右括号的数量 > 0) && (右括号的数量 != 左括号的数量)
        // 选择右括号，然后进入下一层搜索
        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            dfs(idx+1, leftRemain, rightRemain-1, string, list);
        }
    }

    public static void main(String[] args) {
//        StringBuilder builder=new StringBuilder();
//        builder.append("abcd");
//        System.out.println(builder.deleteCharAt(3).toString());
        System.out.println(new generateParenthesis().generateParenthesis(3));
    }
}
