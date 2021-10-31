package Medium.LinkListTest;

/*
*
* 147. Insertion Sort List
Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list


Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5*/


/**
 * @author 马世臣
 * @// TODO: 2020/11/20  */


public class insertionSortList {


    public ListNode insertionSortList(ListNode head) {
        if(head==null) return null;
        ListNode res=new ListNode(0);
        res.next=head;
        while (head!=null&&head.next!=null){
            if(head.val<=head.next.val)
                head=head.next;
            else {
                ListNode temp=res.next;
                ListNode pre=res;
                while (head.next.val>temp.val){
                    temp=temp.next;
                    pre=pre.next;
                }

                //找到了插入点
                ListNode insert=head.next;
                ListNode seq=head.next.next;
                insert.next=temp;
                pre.next=insert;
                head.next=seq;
            }
        }
        return res.next;
    }



    public static void main(String[] args) {
        ListNode node1=new ListNode(4);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(1);
        ListNode node4=new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        System.out.println(new insertionSortList().insertionSortList(node1).val);
    }
}
