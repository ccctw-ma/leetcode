package Medium.BorD_FSTest;



/*
* 430. 扁平化多级双向链表
多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。



示例 1：

输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
解释：

输入的多级列表如下图所示：



扁平化后的链表如下图：


示例 2：

输入：head = [1,2,null,3]
输出：[1,3,2]
解释：

输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
示例 3：

输入：head = []
输出：[]


如何表示测试用例中的多级链表？

以 示例 1 为例：

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
序列化其中的每一级之后：

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。

[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
合并所有序列化结果，并去除末尾的 null 。

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]


提示：

节点数目不超过 1000
1 <= Node.val <= 10^5*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马世臣
 * @// TODO: 2020/8/2  */


public class flatten2 {


    public Node flatten(Node head) {
        if(head==null) return head;
        Node root=head,pre;
        while (head!=null){
            pre=head;
            Node temp=head.next;
            if(head.child!=null){
                Node n=flatten(head.child);
                head.next=n;
                while (n.next!=null) n=n.next;
                n.next=temp;
                head.child=null;
            }
            head=head.next;
            pre.next=head;
            if(head!=null)head.prev=pre;
        }
        return root;
    }


    //简单的通过栈的方式进行先序遍历模拟，只需要做好关键点的处理即可
    public Node flatten2(Node head) {
        Node cur = head,pre = null;
        Deque<Node> s = new ArrayDeque<>();
        while(cur != null || !s.isEmpty()){
            if(cur == null){
                cur = s.pop();
                pre.next = cur;
                cur.prev = pre;
            } else if(cur.child != null){
                if(cur.next != null)    s.push(cur.next);
                cur.child.prev = cur;
                cur.next = cur.child;
                cur.child = null;
                pre = cur;
                cur = cur.next;
            } else if(cur.child == null){
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }



    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    public static void main(String[] args) {

    }
}
