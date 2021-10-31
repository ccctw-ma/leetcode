package Easy.ArrayTest;


/**
 * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
 *
 * 完成所有替换操作后，请你返回这个数组。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr = [17,18,5,4,6,1]
 * 输出：[18,6,6,6,1,-1]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i] <= 10^5
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/2/5 1299. 将每个元素替换为右侧最大元素 */


public class replaceElements {

    public int[] replaceElements(int[] arr) {
        int max=-1;
        for (int i=0;i<arr.length-1;i++){
            if(max==-1||arr[i]==max){
                max=-1;
                for (int j=i+1;j<arr.length;j++){
                    if(arr[j]>max) max=arr[j];
                }

            }
            arr[i]=max;
        }
        arr[arr.length-1]=-1;
        return arr;
    }

    //从后往前，只需遍历一遍即可
    public int[] replaceElements2(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            if (temp > max) {
                max = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {

    }
}
