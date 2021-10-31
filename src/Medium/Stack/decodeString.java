package Medium.Stack;


/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 **/

import java.util.LinkedList;

/**
 * @author 马世臣 
 * @// TODO: 2020/3/4 394. 字符串解码
 * */

public class decodeString {


    public String decodeString(String s) {
        return DeepTraverse(s);
    }

    private String DeepTraverse(String s){
        int n,index=0;
        String temp;
        StringBuilder res= new StringBuilder();
        while (index<s.length()){
            if(Character.isDigit(s.charAt(index))){
                int j=index+1;
                while (Character.isDigit(s.charAt(j))){
                    j++;
                }
                n=Integer.parseInt(s.substring(index,j++));
                int num=1;
                index=j;
                while (j<s.length()&&num!=0){
                    if(s.charAt(j)=='['){
                        num++;
                    }
                    else if(s.charAt(j)==']'){
                        num--;
                    }
                    j++;
                }
                temp=DeepTraverse(s.substring(index,j-1));
                res.append(temp.repeat(Math.max(0, n)));
                index=j;
            }else {
                res.append(s.charAt(index++));
            }
        }
        return res.toString();
    }

    public String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }

    
    public static void main(String[] args) {
        System.out.println(new decodeString().decodeString2("3[a]2[bc3[3[3[b]]]]"));
        //System.out.println("a2[c]".substring(3,4));
    }
}
