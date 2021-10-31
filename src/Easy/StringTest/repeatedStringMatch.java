package Easy.StringTest;



/**
 * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。
 *
 * 举个例子，A = "abcd"，B = "cdabcdab"。
 *
 * 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
 *
 * 注意:
 *
 *  A 与 B 字符串的长度在1和10000区间范围内。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/29 686. 重复叠加字符串匹配 */

public class repeatedStringMatch {


    public int repeatedStringMatch(String A, String B) {
        for (char c:B.toCharArray()){
            if(A.indexOf(c)==-1){
                return -1;
            }
        }
        if(A.length()>B.length()){
            if(A.contains(B)){
                return 1;
            }else {
                return (A+A).contains(B)?2:-1;
            }
        }
        int i=1;
        String temp= A;
        while (!A.contains(B)){
            if(A.length()>B.length()*2) return -1;
            A+=temp;
            i++;
        }
        return i;
    }


    //关键在于终止长度，2*A+B
    public int repeatedStringMatch2(String A, String B) {
        int count = 1;
        StringBuilder data = new StringBuilder(A);
        while (data.length() < B.length()) {
            data.append(A);
            ++count;
        }
        return data.lastIndexOf(B) != -1 ? count : data.append(A).lastIndexOf(B) != -1 ? count + 1 : -1;
    }
    
    public static void main(String[] args) {
        System.out.println(new repeatedStringMatch().repeatedStringMatch("abc","cabcabca"));
    }
}
