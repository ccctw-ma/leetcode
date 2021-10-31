package Easy.SortTest;


/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 *
 * 提示：
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 **/


/**
 * @author 马世臣
 * @// TODO: 2020/1/16  922. 按奇偶排序数组 II*/
public class sortArrayByParityII {
    public static int[] sortArrayByParityII(int[] A) {
        int low=0,high=A.length-1;
        while (true){
            while (low<=A.length-1){
                if(low%2==0&&A[low]%2!=0){
                    break;
                }else {
                    low++;
                }
            }
            while (high>=0){
                if(high%2!=0&&A[high]%2==0){
                    break;
                }else {
                    high--;
                }
            }
            if((low==A.length)||high==-1){
                break;
            }else {
                int temp=A[low];
                A[low]=A[high];
                A[high]=temp;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] array=new int[]{6,4,3,5};
        for (int i:sortArrayByParityII(array)){
            System.out.println(i);
        }
    }
}
