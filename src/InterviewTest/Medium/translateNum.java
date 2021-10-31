package InterviewTest.Medium;


/*
 * 面试题46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 *
 * 提示：
 *
 * 0 <= num < 231*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/9
 * */
public class translateNum {



    private int count;
    public int translateNum(int num) {
        String s=String.valueOf(num);
        count=0;
        int[] arr=new int[s.length()];
        for(int i=0;i<arr.length;i++){
            arr[arr.length-i-1]=num%10;
            num/=10;
        }
        dfs(arr,0);
        return count;
    }

    private void dfs(int[] arr, int index){
        if(index==arr.length){
            count++;
            return;
        }
        dfs(arr,index+1);
        if(index<arr.length-1){
            int a=arr[index];
            int b=arr[index+1];
            int c=a*10+b;
            if(c<=25&&c>=10){
                dfs(arr,index+2);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new translateNum().translateNum(506));
    }
}
