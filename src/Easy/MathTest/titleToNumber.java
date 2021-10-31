package Easy.MathTest;


/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28 
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 * 致谢：
 * 特别感谢 @ts 添加此问题并创建所有测试用例。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 171. Excel表列序号 */


public class titleToNumber {

    public int titleToNumber(String s) {
        int sum=0,index=1;
        for (int i=s.length()-1;i>=0;i--){
            sum+=(s.charAt(i)-'A'+1)*index;
            index*=26;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new titleToNumber().titleToNumber("ZY"));
    }
}
