package Easy.InterviewTest;

import java.util.ArrayList;
import java.util.List;

public class lastRemaining {


    public int lastRemaining(int n, int m) {
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<n;i++) list.add(i);
        int index=0;
        while (list.size()!=1){
            index=(index+m-1)%list.size();
            list.remove(index);
            index=(index)%list.size();
        }
        return list.get(0);
    }


    /**
     * 最终剩下一个人时的安全位置肯定为0，反推安全位置在人数为n时的编号
     * 人数为1： 0
     * 人数为2： (0+m) % 2
     * 人数为3： ((0+m) % 2 + m) % 3
     * 人数为4： (((0+m) % 2 + m) % 3 + m) % 4
     * ........
     * 迭代推理到n就可以得出答案
     *  妙呀 妙呀 妙呀
     **/
    public int lastRemaining2(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }




    public static void main(String[] args) {
        System.out.println(new lastRemaining().lastRemaining(10,17));
    }
}
