package Medium.LinkListTest;


/*
* 19. 删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/1  */


public class removeNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root=head;
        ListNode del=head;
        for(int i=0;i<n;i++)    root=root.next;
        if(root==null) return head.next;
        ListNode pre=head;
        root=root.next;
        del=del.next;
        while (root!=null){
            root=root.next;
            pre=pre.next;
            del=del.next;
        }
        pre.next=del.next;
        return head;
    }

    public static void main(String[] args) {

    }
}
