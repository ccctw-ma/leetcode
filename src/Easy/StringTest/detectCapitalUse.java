package Easy.StringTest;



/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 *
 * 示例 1:
 *
 * 输入: "USA"
 * 输出: True
 * 示例 2:
 *
 * 输入: "FlaG"
 * 输出: False
 * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/28 520. 检测大写字母 */

public class detectCapitalUse {


    public boolean detectCapitalUse(String word) {
        if(word.length()==0) return true;
        int i=0,first=0,flag=0;
        for (char ch:word.toCharArray()){
            if(i==0){
                if(Character.isUpperCase(ch)){
                    first=1;
                }else {
                    first=2;
                }
            }else if(i==1){
                if(Character.isUpperCase(ch)&&first==1){
                    flag=1;
                }else if(Character.isLowerCase(ch)&&first==1){
                    flag=3;
                }else if(Character.isLowerCase(ch)&&first==2){
                    flag=2;
                }else {
                    return false;
                }
            }else {
                if((Character.isLowerCase(ch)&&flag==1)||(Character.isUpperCase(ch)&&(flag==2||flag==3))){
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    public boolean detectCapitalUse2(String word) {
        char[] cs = word.toCharArray();
        int upper = 0;//大写字母个数
        int lower = 0;//小写字母个数
        for(int i=0;i<cs.length;i++) {
            if(cs[i]>='a') {
                lower++;
            }else {
                upper++;
            }
        }
        if(upper==cs.length) { //全大写
            return true;
        }
        if(lower==cs.length) {//全小写
            return true;
        }
        if(upper==1 && cs[0]<'a') {//首字母大写
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Character.isUpperCase('1'));
    }
}
