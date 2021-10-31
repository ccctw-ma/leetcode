package Difficult.StackTest;


/*
* 224. 基本计算器
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。

示例 1:

输入: "1 + 1"
输出: 2
示例 2:

输入: " 2-1 + 2 "
输出: 3
示例 3:

输入: "(1+(4+5+2)-3)+(6+8)"
输出: 23
说明：

你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2020/7/27
 * */


public class calculate {

    public int calculate(String s) {
        Deque<Character> oper=new ArrayDeque<>();
        Deque<Integer> nums=new ArrayDeque<>();
        //确定运算符优先级
        Map<Character,Integer> priority=new HashMap<>();
        priority.put('+',1);
        priority.put('-',1);
        priority.put('*',2);
        priority.put('/',2);
        priority.put('(',0);
        //遍历该集合
        int index=0;
        while (index<s.length()){
            char c=s.charAt(index);
            if(c==' ') {
                index++;
                continue;
            }
            if(Character.isDigit(c)){
                int number=0;
                number+=c-'0';
                index++;
                while (index<s.length()&&Character.isDigit(s.charAt(index))){
                    number*=10;
                    number+=s.charAt(index)-'0';
                    index++;
                }
                nums.push(number);
            }else {
                if(c=='('){
                    oper.push(c);
                }else if(c==')'){
                    while (oper.peek()!='('){
                        operate(nums,oper.poll());
                    }
                    oper.pop();
                }else if(oper.isEmpty()||priority.get(c)>priority.get(oper.peek())){
                    oper.push(c);
                }else {
                    while (!oper.isEmpty()&&priority.get(c)<=priority.get(oper.peek())){
                        operate(nums,oper.poll());
                    }
                    oper.push(c);
                }
                index++;
            }
        }
        while (!oper.isEmpty()){
            operate(nums,oper.poll());
        }
        return nums.peek();
    }

    private void operate(Deque<Integer> nums, char opr){
        int b=nums.pop();
        int a=nums.pop();
        int res=0;
        switch (opr){
            case '+':res=a+b;break;
            case '-':res=a-b;break;
            case '*':res=a*b;break;
            case '/':res=a/b;break;
        }
        nums.push(res);
    }




    public static void main(String[] args) {
        System.out.println(new calculate().calculate("1*2*3*2-1+7-(1+8)"));
    }
}
