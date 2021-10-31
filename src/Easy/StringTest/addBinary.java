package Easy.StringTest;

import java.util.Stack;

public class addBinary {


    public String addBinary(String a, String b) {
        Stack<Character> stack1=new Stack<>();
        Stack<Character> stack2=new Stack<>();
        Stack<Character> stack3=new Stack<>();
        for (char ch:a.toCharArray()){
            stack1.push(ch);
        }
        for (char ch:b.toCharArray()){
            stack2.push(ch);
        }
        int temp=0;
        while (!stack1.isEmpty()||!stack2.isEmpty()){
            if(!stack1.isEmpty()&&!stack2.isEmpty()){
                int s1=stack1.pop()-'0';
                int s2=stack2.pop()-'0';
                int sum=s1+s2+temp;
                if(sum<2){
                    temp=0;
                    stack3.push((char)(sum+'0'));
                }else {
                    temp=1;
                    stack3.push((char)(sum%2+'0'));
                }
            }else if(stack1.isEmpty()){
                int s1=0;
                int s2=stack2.pop()-'0';
                int sum=s1+s2+temp;
                if(sum<2){
                    temp=0;
                    stack3.push((char)(sum+'0'));
                }else{
                    temp=1;
                    stack3.push((char)(sum%2+'0'));
                }
            }else  {
                int s1=stack1.pop()-'0';
                int s2=0;
                int sum=s1+s2+temp;
                if(sum<2){
                    temp=0;
                    stack3.push((char)(sum+'0'));
                }else {
                    temp=1;
                    stack3.push((char)(sum%2+'0'));
                }
            }
        }
        if(temp==1){
            stack3.push('1');
        }
        StringBuilder result= new StringBuilder();
        while (!stack3.isEmpty()){
            result.append(stack3.pop());
        }
        return result.toString();
    }

    public String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1 ;j >=0 || i >= 0; i--, j--) {
            int cur = ca;
            cur += (i >= 0 ? a.charAt(i) - '0': 0);
            cur += (j >= 0 ? b.charAt(j) - '0': 0);
            sb.append(cur % 2);
            ca = cur / 2;
        }
        if (ca > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {

    }
}
