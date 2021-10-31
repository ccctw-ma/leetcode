package Easy.LinkedListTest;



/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 83. 删除排序链表中的重复元素 */


public class deleteDuplicates {


    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode cur=head;
        ListNode other=head.next;
        while (other!=null){
            while (other!=null&&other.val==cur.val){
                other=other.next;
            }
            cur.next=other;
            cur=cur.next;
        }
        return head;
    }
    
    public static void main(String[] args) {

    }
}
