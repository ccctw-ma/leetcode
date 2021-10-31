package Easy.ArrayTest;



/**
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 *
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整数：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * 示例 2：
 *
 * 输入：[60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
 *  
 *
 * 提示：
 *
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/4 1010. 总持续时间可被 60 整除的歌曲 */

public class numPairsDivisibleBy60 {



    //失败的案例
    public int numPairsDivisibleBy60(int[] time) {
        if(time.length==1) return 0;
        int[] bucket=new int[501];
        for (int i:time){
            bucket[i]++;
        }
        int sum=0;
        for (int i=1;i<bucket.length;i++){
            if(bucket[i]>0){
                int next=(i/60+1)*60-i;
                if(next<i) next+=60;
                for (int j=next;j<bucket.length;j+=60){
                    if(i==j){
                        sum+=((bucket[i]-1)*bucket[i])/2;
                    }else {
                        sum+=bucket[i]*bucket[j];
                    }
                }
            }
        }
        return sum;
    }



    //考虑特殊情况
    public int numPairsDivisibleBy602(int[] time) {
        int[] count = new int[60];
        for (int num : time) {
            count[num % 60]++;
        }
        int res = 0;
        for (int i = 1; i < 30; i++) {
            res += count[i] * count[60 - i];
        }
        res += count[0] * (count[0] - 1) / 2;
        res += count[30] * (count[30] - 1) / 2;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new numPairsDivisibleBy60().numPairsDivisibleBy60(new int[]{15,63,451,213,37,209,343,319}));
    }

}
