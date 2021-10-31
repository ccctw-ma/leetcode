package Medium.String;

import java.util.ArrayList;
import java.util.List;


/*
* 842. 将数组拆分成斐波那契序列
给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。

形式上，斐波那契式序列是一个非负整数列表 F，且满足：

0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
F.length >= 3；
对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。

返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。



示例 1：

输入："123456579"
输出：[123,456,579]
示例 2：

输入: "11235813"
输出: [1,1,2,3,5,8,13]
示例 3：

输入: "112358130"
输出: []
解释: 这项任务无法完成。
示例 4：

输入："0123"
输出：[]
解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
示例 5：

输入: "1101111"
输出: [110, 1, 111]
解释: 输出 [11,0,11,11] 也同样被接受。


提示：

1 <= S.length <= 200
字符串 S 中只含有数字。*/

/**
 * @author 马世臣
 * @// TODO: 2020/12/8  */



public class splitIntoFibonacci {



    public List<Integer> splitIntoFibonacci(String S) {
        ans=new ArrayList<>();
        success=false;
        if( S.length()<3) return ans;
        int len=S.length();

        List<Integer> res=new ArrayList<>();
        for (int i=0;i<=len/2;i++){
            if(success||(S.charAt(0)=='0'&&i>0)) break;
            String f1=S.substring(0,i+1);
            try {
                res.add(Integer.parseInt(f1));
            }catch (NumberFormatException e){
                break;
            }
            for (int j=i+1;j<len;j++){
                //给定的字符串长度不满足算术式子
                if(success||Math.max(i+1,j-i+1)+j>len) break;
                String f2 = S.charAt(i+1) == '0' ? "0" :S.substring(i+1,j+1);
                try {
                    res.add(Integer.parseInt(f2));
                }catch (NumberFormatException e){
                    break;
                }
                trace(res,S,f1,f2,j+1);
                res.remove(res.size()-1);

            }
            res.remove(res.size()-1);
        }
        return ans;
    }

    private List<Integer> ans;
    private boolean success;
    private void trace(List<Integer> res,String s,String a,String b,int index){
        if(index==s.length()){
            this.ans=new ArrayList<>(res);
            success=true;
            return;
        }
        String sum = String.valueOf(res.get(res.size()-1)+res.get(res.size()-2));
        int l=sum.length();
        if(index+l>s.length()) return;
        String temp=s.substring(index,index+l);
        if(temp.equals(sum)){
            res.add(Integer.parseInt(sum));
            trace(res,s,b,sum,index+l);
            res.remove(res.size()-1);
        }
    }

    private String add(String a,String b){
        StringBuilder builder=new StringBuilder();
        int maxLen=Math.max(a.length(),b.length());
        int[] arr=new int[maxLen];
        int carry=0,index=0;
        for (int i=a.length()-1;i>=0;i--)
            arr[index++]=a.charAt(i)-'0';
        index=0;
        for (int i=b.length()-1;i>=0;i--){
            int temp = (b.charAt(i)-'0') + arr[index] + carry;
            carry = temp/10;
            arr[index++] = temp%10;
        }
        if(carry==1) builder.append('1');
        for (int i=maxLen-1;i>=0;i--){
            builder.append((char) (arr[i]+'0'));
        }
        return builder.toString();
    }

    private boolean compare(String a){
        String max=String.valueOf(Integer.MAX_VALUE);
        if(a.length()>max.length()) return false;
        try {
            Integer.parseInt(a);
        }catch (Exception e){
        }
        return a.compareTo(max) <= 0;
    }





    public static void main(String[] args) {
        System.out.println(new splitIntoFibonacci().splitIntoFibonacci("17522"));
//        System.out.println(new splitIntoFibonacci().add("17","5"));
    }
}
