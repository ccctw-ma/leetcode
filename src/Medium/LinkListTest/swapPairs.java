package Medium.LinkListTest;

public class swapPairs {


    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next == null)   return head;
        ListNode next=head.next.next;
        ListNode first=head.next;
        first.next=head;
        head.next=swapPairs(next);
        return first;
    }






    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        ListNode root=new swapPairs().swapPairs(node1);
        while (root!=null){
            System.out.print(root.val+" ");
            root=root.next;
        }
    }
}
