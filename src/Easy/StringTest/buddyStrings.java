package Easy.StringTest;



/**
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入： A = "ab", B = "ba"
 * 输出： true
 * 示例 2：
 *
 * 输入： A = "ab", B = "ab"
 * 输出： false
 * 示例 3:
 *
 * 输入： A = "aa", B = "aa"
 * 输出： true
 * 示例 4：
 *
 * 输入： A = "aaaaaaabc", B = "aaaaaaacb"
 * 输出： true
 * 示例 5：
 *
 * 输入： A = "", B = "aa"
 * 输出： false
 *  
 *
 * 提示：
 *
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A 和 B 仅由小写字母构成。
 **/

import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 859. 亲密字符串 */

public class buddyStrings {


    public boolean buddyStrings(String A, String B) {
        if(A.length()!=B.length()) return false;
        char[] charsA=A.toCharArray();
        char[] charsB=B.toCharArray();
        if(A.equals(B)){
            Arrays.sort(charsA);
            for (int i=1;i<charsA.length;i++){
                if(charsA[i]==charsA[i-1]){
                    return true;
                }
            }
            return false;
        }
        int i=0,j=charsA.length-1;
        while (i<=j&&charsA[i]==charsB[i]){
            i++;
        }
        while (i<j&&charsA[j]==charsB[j]){
            j--;
        }
        char temp=charsA[j];
        charsA[j]=charsA[i];
        charsA[i]=temp;
        return String.valueOf(charsA).equals(String.valueOf(charsB));
    }

    public static void main(String[] args) {
        System.out.println(new buddyStrings().buddyStrings("abab","abab"));
    }
}
