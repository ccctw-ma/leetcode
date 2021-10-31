package Easy.ArrayTest;



/**
 *给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 *
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 3：
 *
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 4：
 *
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 * 示例 5：
 *
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 *
 * 提示：
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/3 914. 卡牌分组 */

public class hasGroupsSizeX {

    public boolean hasGroupsSizeX(int[] deck) {
        int[] bucket=new int[10000];
        for (int i:deck){
            bucket[i]++;
        }
        int commonFactor=bucket[0],r,m,n;
        for (int i:bucket){
            if(commonFactor>i){
                m=commonFactor;
                n=i;
            }else if(commonFactor<i){
                m=i;
                n=commonFactor;
            }else {
                continue;
            }
            while(n != 0)
            {
                r = m % n;
                m = n;
                n = r;
            }
            commonFactor=m;
            if(commonFactor==1) return false;
        }
        return commonFactor>=2;
    }

    public boolean hasGroupsSizeX2(int[] deck) {
        int[] arr = new int[1000];
        for(int i: deck) {
            arr[i]++;
        }
        int value =-1;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]!=0) {
                if(value==-1) {
                    value=arr[i];
                }else {
                    value=a(value,arr[i]);
                }
            }
        }
        return value>=2;
    }

    private int a(int value, int i) {
        // 递归求公约数
        return value==0?i:a(i%value,value);
    }
    
    public static void main(String[] args) {

    }
}
