package Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class medium {



    //suffix expression
    /**
     * @apiNote
     * 1、从左到右扫描表达式
     * 2、如果遇见数字直接放入后缀表达式
     * 3、当遇到运算符时
     *  a、如果是'('也直接放入
     *  b、如果是')'则把栈中从栈顶到第一个'('的运算符全部加入后缀表达式
     *  c、若是其它运算符则把从栈顶到第一个'('中所有比它优先级大或相等的运算符全部出栈，进入后缀表达式
     *      反之没有的话也就直接入栈
     * 4、最后把栈中的所有运算符全部出栈加入后缀表达式即可
     *
     * */
    public String translateToSuffix(String s){
        // '+','-','*','/','(',')'
        Map<Character,Integer> map=new HashMap<>();
        map.put('+',1);
        map.put('-',1);
        map.put('*',2);
        map.put('/',2);
        Deque<Character> expression=new ArrayDeque<>();
        Deque<Character> operators=new ArrayDeque<>();
        for (Character c:s.toCharArray()){
            if(c=='('){
                operators.push(c);
            } else if(c==')'){
                while (!operators.isEmpty()&&operators.peek()!='('){
                    expression.addLast(operators.pop());
                }
                operators.pop();
            } else if(map.containsKey(c)){
                if(!operators.isEmpty()&&operators.peek()!='('&&map.get(operators.peek())>=map.get(c)){
                    while (!operators.isEmpty()&&operators.peek()!='('&&map.get(operators.peek())>=map.get(c)){
                        expression.addLast(operators.pop());
                    }
                }
                operators.push(c);
            } else {
                expression.addLast(c);
            }
        }
        while (!operators.isEmpty()){
            expression.addLast(operators.pop());
        }
        StringBuilder builder=new StringBuilder();
        for (Character c:expression){
            builder.append(c);
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(new medium().translateToSuffix("1+2+3+4+5/3/4/4*(1+1*(2*2))"));
    }
}
