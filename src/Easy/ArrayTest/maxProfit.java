package Easy.ArrayTest;



/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * */

/**
 * @author 马世臣 
 * @// TODO: 2020/1/28 121. 买卖股票的最佳时机 */

public class maxProfit {


    public int maxProfit(int[] prices) {
        int best=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        for (int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                if(prices[i-1]<min){
                    min=prices[i-1];
                }
                if(prices[i]-min>best){
                    best=prices[i]-min;
                }
            }
        }
        return best>0?best:0;
    }
    
    public static void main(String[] args) {
        int[] ints=new int[]{3,3,5,0,0,3,1,4};
        System.out.println(new maxProfit().maxProfit(ints));
    }
}
