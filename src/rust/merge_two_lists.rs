#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
use super::utils::list_node::ListNode;

pub struct Solution {}

impl Solution {
    #[allow(unused)]
    pub fn merge_two_lists(mut list1: Option<Box<ListNode>>, mut list2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = ListNode::new(0);
        let mut ptr = &mut dummy;

        while list1.is_some() && list2.is_some() {
            // 找到要被消费的链表
            let smaller = if list1.as_ref().unwrap().val < list2.as_ref().unwrap().val { &mut list1 } else { &mut list2 };

            // 修改本地节点，添加下一个节点
            ptr.next = smaller.to_owned();
            // 移动指针，指向下一个节点
            ptr = ptr.next.as_mut().unwrap();
            // 修改剩余链表队列：更新链表头部；清除指针原有对象。
            *smaller = ptr.next.take();
        }
        ptr.next = if list1.is_none() { list2 } else { list1 };
        dummy.next
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    // use super::*;

    #[test]
    fn test() {
        assert_eq!(1, 1);
    }
}