package Easy.HashTable;



/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/2 409. 最长回文串 */

public class longestPalindrome {


    public int longestPalindrome(String s) {
        int[] bucket=new int[52];
        for (char c:s.toCharArray()){
            if(Character.isLowerCase(c)){
                bucket[c-'a']++;
            }else {
                bucket[c-'A'+26]++;
            }
        }
        int sum1=0,sum2=0;
        for (int i:bucket){
            if(i==1){
                sum1++;
            }else if(i>1){
                sum1+=i&1;
                sum2+=(i>>1);
            }
        }
        return sum1==0?sum2<<1:(sum2<<1)+1;
    }



    //一次遍历，并且没有过多的多余变量，节省了空间
    public int longestPalindrome2(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0)
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new longestPalindrome().longestPalindrome("abccccdddd"));
        System.out.println('Z'-'A');
    }
}
