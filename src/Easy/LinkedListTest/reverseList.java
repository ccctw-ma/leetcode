package Easy.LinkedListTest;


/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 206. 反转链表 */

public class reverseList {


    public ListNode reverseList(ListNode head) {
        ListNode last=null;
        while (head!=null){
            ListNode temp=new ListNode(head.val);
            temp.next=last;
            last=temp;
            head=head.next;
        }
        return last;
    }

    private static ListNode reverseList2(ListNode head){
        ListNode prev = null, curr = head, temp;
        while(curr != null){
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    
    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        ListNode temp=reverseList2(node1);
        while (temp!=null){
            System.out.print(temp.val+" ");
            temp=temp.next;
        }
    }
}
