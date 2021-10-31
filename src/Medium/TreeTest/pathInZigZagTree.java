package Medium.TreeTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/*
* 1104. 二叉树寻路
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。



给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。



示例 1：

输入：label = 14
输出：[1,3,4,14]
示例 2：

输入：label = 26
输出：[1,2,6,10,26]


提示：

1 <= label <= 10^6*/

/**
 * @author 马世臣
 * 21.7.29*/
public class pathInZigZagTree {

    public List<Integer> pathInZigZagTree(int label) {
        int n = (int)(Math.log(label)/Math.log(2));
        int[] arr = new int[n+1];
        arr[n--] = label;
        while (n!=-1){
            label/=2;
            label = 3*(1<<n)-label-1;
            arr[n--] = label;
        }
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Collectors.toList();
        System.out.println(new pathInZigZagTree().pathInZigZagTree(26));
    }
}
