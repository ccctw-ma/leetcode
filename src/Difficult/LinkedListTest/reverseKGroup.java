package Difficult.LinkedListTest;


/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/16  */


public class reverseKGroup {

    private ListNode reverseKGroup(ListNode head, int k) {
        int sum=0;
        ListNode root=head;
        while (root!=null){
            root=root.next;
            sum++;
        }
        ListNode res=new ListNode(1);
        ListNode ans=res;
        while (sum!=0){
            if(sum-k>=0){
                ListNode[] temp=reverse(head,k);
                res.next=temp[1];
                res= traverse(res,k);
                head=temp[0];
                sum-=k;
            }else {
                res.next=head;
                sum=0;
            }
        }
        return ans.next;
    }

    private ListNode traverse(ListNode head,int k){
        while (k>0){
            head=head.next;
            k--;
        }
        return head;
    }

    private ListNode[] reverse(ListNode head,int k){
        ListNode root=null;
        while (k>0){
            ListNode temp=head;
            head=head.next;
            temp.next=root;
            root=temp;
            k--;
        }
        return new ListNode[]{head,root};
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        ListNode node6=new ListNode(6);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        ListNode root=new reverseKGroup().reverseKGroup(node1,2);
        while (root!=null){
            System.out.print(root.val+" ");
            root=root.next;
        }
    }
}
