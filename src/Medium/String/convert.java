package Medium.String;


/*
* 6. Z 字形变换
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/19
 */


public class convert {

    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        int len = s.length();
        int width = (len / (numRows * 2 - 2)) + 1;
        char[][] chars = new char[numRows][width * (numRows - 1)];
        int index = 0, col = 0;
        while (index < len) {
            for (int i = 0; i < numRows && index < len; i++) {
                chars[i][col] = s.charAt(index++);
            }
            for (int i = numRows - 2; i > 0 && index < len; i--) {
                chars[i][++col] = s.charAt(index++);
            }
            col++;
        }
        StringBuilder res = new StringBuilder();
        for (char[] chs : chars) {
            for (char c : chs) {
                if (c != 0) res.append(c);
            }
        }
        return res.toString();
    }


    /*
    * if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
        *  */

    public static void main(String[] args) {
        System.out.println(new convert().convert("LEETCODEISHIRING", 4));
    }
}
