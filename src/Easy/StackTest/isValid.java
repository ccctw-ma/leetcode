package Easy.StackTest;

import java.util.Stack;
/**2020.1.7*/
public class isValid {
    private static  boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for (int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(stack.size()==0){
                stack.push(ch);
            }else if(ch=='('||ch=='['||ch=='{'){
                stack.push(ch);
            }else if(ch==')'&&stack.peek()=='('){
                stack.pop();
            }else if(ch==']'&&stack.peek()=='['){
                stack.pop();
            }else if(ch=='}'&&stack.peek()=='{'){
                stack.pop();
            }else {
                return false;
            }
        }
        return stack.empty();
    }
    public static void main(String[] args) {
        System.out.println(isValid("{[}["));

    }
}
