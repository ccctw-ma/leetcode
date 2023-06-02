#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
use crate::rust::utils::list_node::ListNode;

pub struct Solution {}

impl Solution {
    pub fn delete_duplicates(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        // if head.is_none() {
        //     return head;
        // }
        // let mut node = head.as_mut().unwrap();
        // let mut duplicate = node.val;
        //
        // while let Some(next) = node.next.take() { // 先夺
        //     if next.val == duplicate {
        //         node.next = next.next;
        //     } else {
        //         duplicate = next.val; // 更新下次比较值
        //         node.next = Some(next); // 放回去
        //         node = node.next.as_mut().unwrap(); // 慢指针前进
        //     }
        // }
        // head
        if head.is_none() {
            return None;
        }

        let mut mut_head = head;
        //嵌套结构,as_mut()可以生成一个新实例，包含内容的修改入口
        //最近的唯一值链节点修改入口,本身以mut声明,它自己的内存指针值是会被修改
        let mut latest_uni_box = mut_head.as_mut().unwrap();

        //需要一个独立的分叉的next节点：next_box,可对其改变，截取移动其next子域内容。
        //这样它就不是原来链的第二个可修改入口，不会违反Rust所有权变量不能有2个可写入口
        //std::mem::replace作用是不会产生partial move，破坏latest_uni_box的所有权而不能再往下的程序被使用
        while let Some(next_box) = std::mem::replace(&mut latest_uni_box.next, None) {
            //println!("maybe {} vs latest_uni {}",dyn_head_box.val,next_box.val );
            //前面最近的唯一值链节点
            let latest_uni_val = latest_uni_box.val;
            //后一个可能重复的链节点
            let maybe_dul_val = next_box.val;

            if maybe_dul_val == latest_uni_val {
                //latest_uni_box.next需要变
                latest_uni_box.next = next_box.next;
            } else {
                //不相等，maybe_dule转变为新的latest_uni
                //1.恢复replace latest_uni_box.next 为空
                latest_uni_box.next = Some(next_box);
                //2.链的可变引用修改入口（不改变头节点所有权），指向下一个唯一节点的修改入口，&mut
                latest_uni_box = latest_uni_box.next.as_mut().unwrap();
            }

            //边界条件，走到最后
            if latest_uni_box.next.is_none() {
                break;
            }
        }

        mut_head
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::list_node::ListNode;
    use super::*;

    fn build_list_from_slice(s: &[i32]) -> Option<Box<ListNode>> {
        if s.is_empty() {
            return None;
        }
        let head = Box::new(ListNode {
            val: s.first().copied().unwrap(),
            next: build_list_from_slice(&s[1..]),
        });
        Some(head)
    }


    #[test]
    fn test_delete_duplicates() {
        struct TestCase {
            name: &'static str,
            nums: &'static [i32],
            expect: &'static [i32],
        }

        vec![
            TestCase {
                name: "basic",
                nums: &[1, 1, 2, 3, 3],
                expect: &[1, 2, 3],
            },
        ].iter().for_each(|testcase| {
            let head = build_list_from_slice(testcase.nums);
            let actual = Solution::delete_duplicates(head);
            let expect = build_list_from_slice(testcase.expect);
            assert_eq!(expect, actual, "{} failed", testcase.name);
        });
    }
}
