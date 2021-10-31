package Medium.ArrayTest;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/*
* 241. 为运算表达式设计优先级
给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。

示例 1:

输入: "2-1-1"
输出: [0, 2]
解释:
((2-1)-1) = 0
(2-(1-1)) = 2
示例 2:

输入: "2*3-4*5"
输出: [-34, -14, -10, -10, 10]
解释:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/21
 * */


public class diffWaysToCompute {

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> list=new ArrayList<>();
        if(input.length()==0) return list;
        List<Integer> res=traverse(input,0,input.length()-1);
        res.sort(Comparator.comparingInt(o -> o));
        return res;
     }

     private List<Integer> traverse(String input,int l,int r){
        List<Integer> ans=new ArrayList<>();
        int num=0;
        boolean flag=true;
        for (int i=l;i<=r;i++){
            char c=input.charAt(i);
            if (Character.isDigit(c)){
                num*=10;
                num+=(c-'0');
            }else {
                flag=false;
                break;
            }
        }
        if(flag){
            ans.add(num);
            return ans;
        }
        for (int i=l;i<=r;i++){
            char c=input.charAt(i);
            if(Character.isDigit(c)) continue;
            List<Integer> left=traverse(input,l,i-1);
            List<Integer> right=traverse(input,i+1,r);
            for (int a:left){
                for (int b:right){
                    if(c=='+') ans.add(a+b);
                    if(c=='-') ans.add(a-b);
                    if(c=='*') ans.add(a*b);
                }
            }
        }
        return ans;
     }

    public static void main(String[] args) {
        System.out.println(new diffWaysToCompute().diffWaysToCompute("20-21-21"));
    }
}
