package Medium.TreeTest;


/*
* 508. 出现次数最多的子树元素和
给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。

你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。



示例 1：
输入:

  5
 /  \
2   -3
返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。

示例 2：
输入：

  5
 /  \
2   -5
返回 [2]，只有 2 出现两次，-5 只出现 1 次。



提示： 假设任意子树元素和均可以用 32 位有符号整数表示。*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2020/8/7
 * */

public class findFrequentTreeSum {

    public int[] findFrequentTreeSum(TreeNode root) {
        map=new HashMap<>();
        times=new HashMap<>();
        max=Integer.MIN_VALUE;
        postraverse(root);
        List<Integer> res=times.get(max);
        int[] ans=new int[res.size()];
        for (int i=0;i<res.size();i++){
            ans[i]=res.get(i);
        }
        return ans;
    }

    private int max;
    private Map<Integer,Integer> map;
    private Map<Integer, List<Integer>> times;
    private int postraverse(TreeNode root){
        if(root==null) return 0;
        int l=postraverse(root.left);
        int r=postraverse(root.right);
        int sum=l+r+root.val;
        int time=map.getOrDefault(sum,0)+1;
        max=Math.max(max,time);
        map.put(sum,time);
        List<Integer> list=times.getOrDefault(time,new ArrayList<>());
        list.add(sum);
        times.put(time,list);
        return sum;
    }

    public static void main(String[] args) {

    }
}
