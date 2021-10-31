package InterviewTest.Medium;


/*
*
* 面试题 17.09. 第 k 个数
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

示例 1:

输入: k = 5

输出: 9*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/29  */




public class getKthMagicNumber {


    public int getKthMagicNumber(int k) {
        int[] res=new int[k];
        res[0]=1;
        int i3=0,i5=0,i7=0;
        for (int i=1;i<k;i++){
            int temp=Math.min(Math.min(res[i3]*3,res[i5]*5),res[i7]*7);
            res[i]=temp;
            if(temp==res[i3]*3) i3++;
            if(temp==res[i5]*5) i5++;
            if(temp==res[i7]*7) i7++;
        }
        return res[k-1];
    }


    public static void main(String[] args) {
        System.out.println(new getKthMagicNumber().getKthMagicNumber(9));
    }
}
