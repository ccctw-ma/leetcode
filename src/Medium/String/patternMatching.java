package Medium.String;


/*
* 面试题 16.18. 模式匹配
你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。

示例 1：

输入： pattern = "abba", value = "dogcatcatdog"
输出： true
示例 2：

输入： pattern = "abba", value = "dogcatcatfish"
输出： false
示例 3：

输入： pattern = "aaaa", value = "dogcatcatdog"
输出： false
示例 4：

输入： pattern = "abba", value = "dogdogdogdog"
输出： true
解释： "a"="dogdog",b=""，反之也符合规则
提示：

0 <= len(pattern) <= 1000
0 <= len(value) <= 1000
你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/22  */


public class patternMatching {


    public boolean patternMatching(String pattern, String value) {
        if(pattern.length()==0&&value.length()==0) return true;
        if(pattern.length()==0&&value.length()!=0) return false;
        int pLen=pattern.length();
        int vLen=value.length();
        char a=pattern.charAt(0);
        int k=1;
        while (k<pLen&&pattern.charAt(k)==a) k++;
        for (int i=0;i<=vLen;i++){
            for (int j=0;j<=vLen;j++){
                String aa=value.substring(0,i);
                String bb=null;
                if(k<pLen){
                    int index=k*i;
                    if(index+j<=vLen){
                        bb=value.substring(index,index+j);
                    }else {
                        continue;
                    }
                }
                if(aa.equals(bb)) continue;
                if(dfs(pattern,value,aa,bb,a)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(String pattern, String value,String aa,String bb,char a){
        int l=aa.length();
        int r=bb==null?0:bb.length();
        int index=0;
        for (int i=0;i<pattern.length();i++){
            char ch=pattern.charAt(i);
            if(ch==a){
                if(index+l>value.length()||!aa.equals(value.substring(index,index+l))){
                    return false;
                }
                index+=l;
            }else {
                if(index+r>value.length()||!bb.equals(value.substring(index,index+r))){
                    return false;
                }
                index+=r;
            }
        }
        return index == value.length();
    }

    public boolean patternMatching2(String pattern, String value) {
        int count_a = 0, count_b = 0;
        for (char ch: pattern.toCharArray()) {
            if (ch == 'a') {
                ++count_a;
            } else {
                ++count_b;
            }
        }
        if (count_a < count_b) {
            int temp = count_a;
            count_a = count_b;
            count_b = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }
        if (value.length() == 0) {
            return count_b == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        for (int len_a = 0; count_a * len_a <= value.length(); ++len_a) {
            int rest = value.length() - count_a * len_a;
            if ((count_b == 0 && rest == 0) || (count_b != 0 && rest % count_b == 0)) {
                int len_b = (count_b == 0 ? 0 : rest / count_b);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch: pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;
    }




    public static void main(String[] args) {
        System.out.println(new patternMatching().patternMatching("a",""));
//        System.out.println(new patternMatching().dfs("abba","dogcatcatdog","dog","cat",'a'));
    }
}
