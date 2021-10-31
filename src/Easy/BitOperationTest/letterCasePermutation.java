package Easy.BitOperationTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 *
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 *
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/18  784. 字母大小写全排列*/


public class letterCasePermutation {


    public static List<String> letterCasePermutation(String s) {
        List<String> list=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if ((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                list2.add(i);
            }
        }
        int[] charIndexs=new int[list2.size()];
        int index=0;
        for (int i:list2){
            charIndexs[index++]=i;
        }
        sortStr(charIndexs,0,list,s);
        return list;
    }

    public static void sortStr(int[] index,int p,List<String> list,String s){
        if(p==index.length){
            list.add(s);
            return;
        } else {
            for (int i=0;i<2;i++){
                if(i==0){
                    sortStr(index,p+1,list,s);
                }else {
                    char[] chars=s.toCharArray();
                    if(chars[index[p]]>='a'&&chars[index[p]]<='z'){
                        chars[index[p]]-=32;
                    }else {
                        chars[index[p]]+=32;
                    }
                    StringBuilder stringBuilder=new StringBuilder();
                    for (char c:chars){
                        stringBuilder.append(c);
                    }
                    sortStr(index,p+1,list,stringBuilder.toString());
                }
            }
        }


    }


    /**
     * class Solution {
     *         List<String> res = new ArrayList<>();
     *     int dis = 'A' - 'a';
     *
     *     public List<String> letterCasePermutation(String S) {
     *         if(S.length()==0) return res;
     *         char[] cs = S.toCharArray();
     *         dfs(cs,0);
     *         return res;
     *     }
     *
     *     public void dfs(char[] cs, int idx){
     *         res.add(String.valueOf(cs));
     *         for(int i = idx; i < cs.length; i++){
     *             if(cs[i] >= '0' && cs[i] <= '9'){
     *                 continue;
     *             }else if(cs[i] >= 'a' && cs[i] <= 'z'){
     *                 cs[i] = (char)(cs[i] + dis);
     *                 dfs(cs,i + 1);//这是以该字母变换的方式继续运行
     *                 cs[i] = (char)(cs[i] - dis);//这是以原状继续运行
     *             }else {
     *                 cs[i] = (char)(cs[i] - dis);
     *                 dfs(cs,i + 1);
     *                 cs[i] = (char)(cs[i] + dis);
     *             }
     *         }
     *     }
     * }*/

    public static void main(String[] args) {
        for (String s:letterCasePermutation("C2a2")){
            System.out.println(s);
        }
    }
}
