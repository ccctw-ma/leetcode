package Medium.BackTrackingTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/*
* 216. 组合总和 III
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]*/

/**
 * @author 马世臣
 * @// TODO: 2020/9/11
 * */



public class combinationSum3 {



    private List<List<Integer>> res;
    private int k;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res=new ArrayList<>();
        this.k=k;
        trace(new ArrayDeque<>(),n,1);
        return res;
    }

    private void trace(Deque<Integer> list, int tar, int index){
        if(tar<0) return;

        if(list.size()==k&&tar==0){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i=index;i<=9&&(tar-i)>=0;i++){
            list.addLast(i);
            trace(list,tar-i,i+1);
            list.removeLast();
        }
    }


    public static void main(String[] args) {
        System.out.println(new combinationSum3().combinationSum3(3,3));
    }
}
