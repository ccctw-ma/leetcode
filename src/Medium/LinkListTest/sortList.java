package Medium.LinkListTest;


/**
 * 148. 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/1  */


public class sortList {


    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode slow=head,fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode temp=slow.next;
        slow.next=null;
        head=sortList(head);
        temp=sortList(temp);
        return merge(head,temp);
    }



    //bottom to up o(1)空间的相关操作
    public  ListNode sortList2(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        // 先统计长度f
        ListNode p = dummyHead.next;
        int length = 0;
        while(p != null){
            ++length;
            p = p.next;
        }
        // 循环开始切割和合并
        for(int size = 1; size < length; size <<= 1){
            ListNode cur = dummyHead.next;
            ListNode tail = dummyHead;
            while(cur != null){
                ListNode left = cur;
                ListNode right = cut(cur, size); // 链表切掉size 剩下的返还给right
                cur = cut(right, size); // 链表切掉size 剩下的返还给cur
                tail.next = merge(left, right);
                while(tail.next != null){
                    tail = tail.next; // 保持最尾端
                }
            }
        }
        return dummyHead.next;
    }

    /**
     * 将链表L切掉前n个节点 并返回后半部分的链表头
     * @param head
     * @param n
     * @return
     */
    public static ListNode cut(ListNode head, int n){
        if(n <= 0) return head;
        ListNode p = head;
        // 往前走n-1步
        while(--n > 0 && p != null){
            p = p.next;
        }
        if(p == null) return null;
        ListNode next = p.next;
        p.next = null;
        return next;
    }

    private ListNode merge(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode l1=new ListNode(-1);
        ListNode l2=new ListNode(5);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(0);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        ListNode res=new sortList().sortList(l1);
        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }

    }
}
