#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
use std::rc::Rc;
use std::cell::RefCell;
use crate::rust::utils::tree::TreeNode;

pub struct Solution {}


impl Solution {
    #[allow(unused)]
    pub fn sufficient_subset(root: Option<Rc<RefCell<TreeNode>>>, mut limit: i32) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() {
            return None;
        }


        if root.as_ref().unwrap().borrow().left.is_none() && root.as_ref().unwrap().borrow().right.is_none() {
            return if root.as_ref().unwrap().borrow().val < limit {
                None
            } else {
                root
            };
        }


        let val = root.as_ref().unwrap().borrow_mut().val;

        let left = Self::sufficient_subset(root.as_ref().unwrap().borrow_mut().left.take(), limit - val);
        root.as_ref().unwrap().borrow_mut().left = left;

        let right = Self::sufficient_subset(root.as_ref().unwrap().borrow_mut().right.take(), limit - val);
        root.as_ref().unwrap().borrow_mut().right = right;

        {
            let r = root.as_ref().unwrap().borrow();
            if r.left.is_none() && r.right.is_none() {
                return None;
            }
        }

        root
    }
}

// submission codes end

#[cfg(test)]
mod tests {

    #[test]
    fn test() {
        assert_eq!(1, 1);
    }
}