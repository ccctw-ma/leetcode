package Medium.LinkListTest;


/*
* 143. Reorder List
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*
*
*
* */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马世臣
 * @// TODO: 2020/10/20  */



public class reorderList {


    //this method may be useful ,however, it is not comply with topic's requirements
    public void reorderList(ListNode head) {
        if(head==null) return;
        Deque<ListNode> deque=new ArrayDeque<>();
        ListNode p=head;
        while (p!=null){
            deque.push(p);
            p=p.next;
        }
        while (head!=deque.peek()&&head.next!=deque.peek()){
            ListNode temp=deque.pop();
            temp.next=head.next;
            head.next=temp;
            head=head.next.next;
        }
        if(head==deque.peek()) head.next=null;
        if(head.next==deque.peek()) head.next.next=null;

    }

    public void reorderList2(ListNode head){
        //Firstly, use slow and fast points to locate this linkedList's middle node
        if(head==null) return;
        ListNode slow=head,fast=head;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode mid=slow.next;
        ListNode p=head;
        slow.next=null;
        // reverse the second half linkedList
        mid=reverse(mid);
        // merge the first and second part
        ListNode p1,p2;
        while (p != null && mid != null){
            p1 = p.next;
            p2 = mid.next;

            p.next = mid;
            p = p1;

            mid.next = p;
            mid = p2;
        }
    }

    private ListNode reverse(ListNode head){
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }



    public void reorderList3(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(head, l2);
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }



    public static void main(String[] args) {
        ListNode p1=new ListNode(1);
        ListNode p2=new ListNode(2);
        ListNode p3=new ListNode(3);
        ListNode p4=new ListNode(4);
        ListNode p5=new ListNode(5);
        p1.next=p2;
        p2.next=p3;
        p3.next=p4;
        p4.next=p5;
        new reorderList().reorderList2(p1);
        while (p1!=null){
            System.out.println(p1.val);
            p1=p1.next;
        }
    }
}
