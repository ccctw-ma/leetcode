package Medium.BorD_FSTest;

public class sortedListToBST {


    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        ListNode mid=middleNode(head,null);
        TreeNode root=new TreeNode(mid.val);
        root.left=buildTree(head,mid);
        root.right=buildTree(mid.next,null);
        return root;
    }

    private TreeNode buildTree(ListNode head, ListNode end){
        if(head==end) return null;
        ListNode mid=middleNode(head,end);
        TreeNode root=new TreeNode(mid.val);
        root.left=buildTree(head,mid);
        root.right=buildTree(mid.next,end);
        return root;
    }

    private ListNode middleNode(ListNode head, ListNode end) {
        ListNode slow=head,fast=head;
        while (fast.next!=end&&fast.next.next!=end){
            slow=slow.next;
            fast=fast.next.next;
        }
        if(fast.next!=end) return slow.next;//链表个数为偶数
        return slow;//个数为奇数
    }



    //上述思想的代码可以进行合并
    public TreeNode sortedListToBST2(ListNode head) {
        if(head == null) return null;
        else if(head.next == null) return new TreeNode(head.val);
        ListNode pre = head;
        ListNode p = pre.next;
        ListNode q = p.next;
        //找到链表的中点p
        while(q!=null && q.next!=null){
            pre = pre.next;
            p = pre.next;
            q = q.next.next;
        }
        //将中点左边的链表分开
        pre.next = null;
        TreeNode root = new TreeNode(p.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(p.next);
        return root;
    }

    public static void main(String[] args) {

    }
}
