package Medium.DynamicTest;



/**
 * 1105. 填充书架
 * 附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。
 *
 * 你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。
 *
 * 按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。
 *
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 *
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 *
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 *
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 *
 *
 *
 * 示例：
 *
 *
 *
 * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * 输出：6
 * 解释：
 * 3 层书架的高度和为 1 + 3 + 2 = 6 。
 * 第 2 本书不必放在第一层书架上。
 *
 *
 * 提示：
 *
 * 1 <= books.length <= 1000
 * 1 <= books[i][0] <= shelf_width <= 1000
 * 1 <= books[i][1] <= 1000*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/26  */

public class minHeightShelves {


    //还是没有理解题意，题目要求数的摆放顺序应该与原来一致，如果不是这样摆放的话就是dp问题了，因为
    //有了后效性
    public int minHeightShelves(int[][] books, int shelf_width) {
        //dp[i]表示前i本书放置的最小高度，最后求最后一本books的dp[books.length],即答案
        int[] dp = new int[books.length+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<dp.length;i++){
            int eachwidth = 0;
            int h = 0;
            //j是书本books数组的索引，譬如当i=2，表示前2本书，那么对应books就是books[0],books[1]的情况
            for(int j=i-1;j>=0;j--){
                eachwidth +=books[j][0];
                if(eachwidth>shelf_width){
                    break;
                }
                h=Math.max(h,books[j][1]);
                dp[i]=Math.min(dp[i],dp[j]+h);
            }
        }
        return dp[books.length];
    }

    public static void main(String[] args) {

    }
}
