package Medium.LinkListTest;


/*
* 92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。


示例 1：


输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
示例 2：

输入：head = [5], left = 1, right = 1
输出：[5]


提示：

链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n


进阶： 你可以使用一趟扫描完成反转吗？*/

/**
 * @author 马世臣
 * @// TODO: 2021/3/18  */


public class reverseBetween {


    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left==right) return head;
        ListNode temp = head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode ans = pre;
        for (int i=1;i<left;i++){
            pre = temp;
            temp = temp.next;
        }
        ListNode a = temp;
        for (int i=left;i<right;i++){
            temp = temp.next;
        }
        ListNode end = temp.next;
        temp.next = null;
        pre.next = reverse(a);
        while (pre.next!=null) pre = pre.next;
        pre.next = end;
        return ans.next;
    }

    private ListNode reverse(ListNode head){
        ListNode pre = null;
        while (head!=null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head =temp;
        }
        return pre;
    }


    public static void main(String[] args) {
        System.out.println((0.60*30)+(41.0/50)*70);
    }
}
