package Easy.LinkedListTest;


/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5*/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 203. 移除链表元素 */


public class removeElements {

    public ListNode removeElements(ListNode head, int val) {
        while (head!=null&&head.val==val) head=head.next;
        ListNode index=head,find;
        while (index!=null&&index.next!=null){
            while (index.next!=null&&index.next.val!=val){
                index=index.next;
            }
            find=index.next;
            while (find!=null&&find.val==val){
                find=find.next;
            }
            index.next=find;
        }
        return head;
    }



    //创建一个开头，应对起点要被删除的问题
    public ListNode removeElements2(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while(cur.next != null){
            if(cur.next.val == val ){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return header.next;
    }

    public static void main(String[] args) {

    }
}
