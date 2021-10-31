package Medium.LinkListTest;


/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 *
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/14  */


public class addTwoNumbers {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder builder1=new StringBuilder();
        StringBuilder builder2=new StringBuilder();
        while (l1!=null){
            builder1.append(l1.val);
            l1=l1.next;
        }
        while (l2!=null){
            builder2.append(l2.val);
            l2=l2.next;
        }
        int carry=0,len1=builder1.length()-1,len2=builder2.length()-1;
        StringBuilder res=new StringBuilder();
        while (len1>=0||len2>=0){
            int a=(len1<0?0:builder1.charAt(len1)-'0');
            int b=(len2<0?0:builder2.charAt(len2)-'0');
            res.insert(0,(a+b+carry)%10);
            carry=(a+b+carry)/10;
            len1--;
            len2--;
        }
        if(carry==1) res.insert(0,carry);
        ListNode node=new ListNode(res.charAt(0)-'0');
        ListNode head=node;
        int i=1;
        while (i<res.length()){
            node.next=new ListNode(res.charAt(i++)-'0');
            node=node.next;
        }
        return head;
    }



    //使用链表反转法
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode header = new ListNode(0);
        ListNode tmp = header;
        l1 = reverse(l1);
        l2 = reverse(l2);

        int carry = 0;
        int sum = 0;

        while(l1!=null||l2!=null){
            if(l1!=null&&l2!=null){
                sum = l1.val+l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }else if(l1!=null){
                sum = l1.val+carry;
                l1= l1.next;
            }else{
                sum = l2.val+carry;
                l2 = l2.next;
            }

            carry = sum>=10?1:0;
            sum = sum%10;
            header.next = new ListNode(sum);
            header = header.next;
            if((l1==null || l2==null)&& carry==0){
                header.next = l1!=null?l1:l2;
                return reverse(tmp.next);
            }
        }

        if(carry!=0){
            header.next = new ListNode(carry);
            header.next.next = null;
        }

        return reverse(tmp.next);

    }

    private ListNode reverse(ListNode head){
        ListNode next;
        ListNode pre=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode n1=new ListNode(7);
        n1.next=new ListNode(2);
        n1.next.next=new ListNode(8);
        n1.next.next.next=new ListNode(1);
        System.out.println(new addTwoNumbers().reverse(n1));
    }
}
