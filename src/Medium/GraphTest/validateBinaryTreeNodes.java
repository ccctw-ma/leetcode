package Medium.GraphTest;


/**
 * 1361. 验证二叉树
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 *
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 *
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
 *
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * 输出：true
 * 示例 2：
 *
 *
 *
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * 输出：false
 * 示例 3：
 *
 *
 *
 * 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * 输出：false
 * 示例 4：
 *
 *
 *
 * 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= n <= 10^4
 * leftChild.length == rightChild.length == n
 * -1 <= leftChild[i], rightChild[i] <= n - 1*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/22  */


public class validateBinaryTreeNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int count=0,index=0;
        int[] grades=new int[n];
        //入度
        for (int i=0;i<n;i++){
            if(leftChild[i]!=-1){
                grades[leftChild[i]]++;
                //二叉树的入度不得大于1
                if(grades[leftChild[i]]>1) return false;
            }
            if(rightChild[i]!=-1){
                grades[rightChild[i]]++;
                if(grades[rightChild[i]]>1) return false;
            }
        }
        //入度等于0的节点只能有一个
        for (int i=0;i<n;i++){
            if(grades[i]==0){
                index=i;
                count++;
            }
        }
        if(count!=1) return false;

        //对于根节点只有在n==1时可以出度为0
        return n==1?leftChild[index]==-1&&rightChild[index]==-1:leftChild[index]!=-1||rightChild[index]!=-1;
    }
}
