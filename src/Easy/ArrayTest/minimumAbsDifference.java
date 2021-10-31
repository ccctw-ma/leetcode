package Easy.ArrayTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你个整数数组 arr，其中每个元素都 不相同。
 *
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * 示例 2：
 *
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * 示例 3：
 *
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *  
 *
 * 提示：
 *
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/4 1200. 最小绝对差 */

public class minimumAbsDifference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        for (int i:arr){
            if(i<min) min=i;
            if(i>max) max=i;
        }
        int[] bucket=new int[max-min+1];
        for (int i:arr){
            bucket[i-min]++;
        }
        int miniDif=Integer.MAX_VALUE;
        for (int i=0;i<bucket.length;i++){
            if(bucket[i]!=0){
                int j=i+1;
                while (j<bucket.length&&bucket[j]==0) j++;
                if((j!=bucket.length)&&(j-i)<miniDif) miniDif=j-i;
            }
        }
        List<List<Integer>> listList=new ArrayList<>();
        for (int i=0;i<bucket.length;i++){
            if(bucket[i]!=0&&(i+miniDif<bucket.length)&&bucket[i+miniDif]!=0){
                List<Integer> list=new ArrayList<>();
                list.add(i+min);
                list.add(i+miniDif+min);
                listList.add(list);
            }
        }
        return listList;
    }

    public List<List<Integer>> minimumAbsDifference2(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            min = Math.min(min, arr[i + 1] - arr[i]);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == min) {
                ans.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        System.out.println(new minimumAbsDifference().minimumAbsDifference(new int[]{1,3,6,10,15}));
    }
}
