package Medium.Stack;


/**
 *
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 *
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 *
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 *
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 *
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 *
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 *
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"*/


import java.util.Stack;

/**
 * @author 马世臣
 * @// TODO: 2020/2/26  71. 简化路径*/

public class simplifyPath {


/*"/a/../../b/../c//.//"*/
    public String simplifyPath(String path) {
        Stack<String> stack=new Stack<>();
        int i=0;
        while (i<path.length()){
            while (i<path.length()&&path.charAt(i)=='/') i++;
            if(i==path.length()) break;
            int j=i;
            while (j<path.length()&&path.charAt(j)!='/') j++;
            String temp=path.substring(i,j);
            if(temp.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else if(!temp.equals(".")){
                stack.push("/"+temp);
            }
            i=j;
        }
        StringBuilder res=new StringBuilder();
        if(stack.isEmpty()) return "/";
        while (!stack.isEmpty()){
            res.insert(0,stack.pop());
        }
        return res.toString();
    }


    //        int index=0;
//        while (index<path.length()){
//            while (index<path.length()&&path.charAt(index)=='/') index++;
//            if(index==path.length()) break;
//            if(index+1<path.length()&&path.charAt(index)=='.'&&path.charAt(index+1)=='.'){
//                if(!stack.isEmpty()){
//                    while (stack.peek()!='/'){
//                        stack.pop();
//                    }
//                    stack.pop();
//                }
//                index+=2;
//            }else if(index<path.length()&&path.charAt(index)=='.'){
//                index++;
//            }else if(index<path.length()&&path.charAt(index)!='.'){
//                stack.push('/');
//                while (index<path.length()&&path.charAt(index)!='/'){
//                    stack.push(path.charAt(index++));
//                }
//            }
//        }
//        StringBuilder s= new StringBuilder();
//        while (!stack.isEmpty()){
//            s.insert(0, stack.pop());
//        }
//        if(s.length()==0) s.append('/');
//        return s.toString();
    public static void main(String[] args) {
        //System.out.println(new simplifyPath().simplifyPath("/a//b////c/d//././/.."));
        String[] strings="/a//b////c/d//././/..".split("/");
        for (int i=0;i<strings.length;i++){
            if(strings[i].length()!=0){
                System.out.println(strings[i]);
            }
        }
    }
}
