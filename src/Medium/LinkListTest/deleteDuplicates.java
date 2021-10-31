package Medium.LinkListTest;

import java.util.Arrays;

public class deleteDuplicates {


    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = head,pre = dummy;
        while (temp!=null){
            if(temp.next!=null&&temp.next.val==temp.val){
                ListNode node = temp;
                while (node!=null&&node.val == temp.val){
                    node = node.next;
                }
                pre.next = node;
                temp = node;
            }else {
                pre = temp;
                temp = temp.next;
            }
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        Arrays.sort(new int[]{1,2,3,45,5});
    }
}
