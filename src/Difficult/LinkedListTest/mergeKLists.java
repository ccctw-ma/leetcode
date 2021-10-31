package Difficult.LinkedListTest;


/* *
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * */


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 马世臣
 * @// TODO: 2020/4/26
 * */


public class mergeKLists {



    //还可以使用分治算法
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode root=new ListNode(0);
        PriorityQueue<ListNode> queue=new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node:lists){
            if(node!=null){
                queue.offer(node);
            }
        }
        ListNode head=root;
        while (!queue.isEmpty()){
            ListNode node=queue.poll();
            root.next=new ListNode(node.val);
            root=root.next;
            if(node.next!=null){
                queue.add(node.next);
            }
        }
        return head.next;
    }


    public static void main(String[] args) {

    }
}
