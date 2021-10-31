package Easy.StringTest;




/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 *
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 *
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 *
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/29 696. 计数二进制子串 */

public class countBinarySubstrings {


    public int countBinarySubstrings(String s) {
        if(s.length()==0) return 1;
        int sum=0,one,zero;
        for (int i=0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                zero=i;
                while (++zero<s.length()&&s.charAt(zero)=='0');
                one=zero;
                while (one<s.length()&&s.charAt(one)=='1'){
                    if(one-zero+1==zero-i){
                        sum++;
                        break;
                    }
                    one++;
                }
            }else {
                one=i;
                while (++one<s.length()&&s.charAt(one)=='1');
                zero=one;
                while (zero<s.length()&&s.charAt(zero)=='0'){
                    if(zero-one+1==one-i){
                        sum++;
                        break;
                    }
                    zero++;
                }
            }
        }
        return sum;
    }


    //方法一：按字符分组
    //我们可以将字符串 s 转换为 groups 数组表示字符串中相同字符连续块的长度。例如，如果 s=“11000111000000”，则 groups=[2，3，4，6]。
    //
    //对于 '0' * k + '1' * k 或 '1' * k + '0' * k 形式的每个二进制字符串，此字符串的中间部分必须出现在两个组之间。
    //
    //让我们尝试计算 groups[i] 和 groups[i+1] 之间的有效二进制字符串数。如果我们有 groups[i] = 2, groups[i+1] = 3，那么它表示 “00111” 或 “11000”。显然，我们可以在此字符串中生成 min(groups[i], groups[i+1]) 有效的二进制字符串。
    //
    //算法：
    //
    //让我们创建上面定义的 groups。s 的第一个元素属于它自己的组。每个元素要么与前一个元素不匹配，从而开始一个大小为 1 的新组；要么匹配，从而使最近一个组的大小增加 1。
    //然后，我们将取 min(groups[i-1], groups[i]) 的和。
    public int countBinarySubstrings2(String s) {
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                groups[++t] = 1;
            } else {
                groups[t]++;
            }
        }

        int ans = 0;
        for (int i = 1; i <= t; i++) {
            ans += Math.min(groups[i-1], groups[i]);
        }
        return ans;
    }



    public int countBinarySubstrings3(String s) {
        int ans = 0, prev = 0, cur = 1;//prev记录上一个连续集合的长度，cur记录当前连续集合的长度
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return ans + Math.min(prev, cur);
    }

    
    public static void main(String[] args) {
        //System.out.println("1100111110110110011111");
        System.out.println(new countBinarySubstrings().countBinarySubstrings("1100111110110110011111"));
    }
}
