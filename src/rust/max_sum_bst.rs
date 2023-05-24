#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
use std::rc::Rc;
use std::cell::RefCell;
use super::utils::tree::TreeNode;

pub struct Solution {}


impl Solution {

    #[allow(unused)]
    pub fn max_sum_bst(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, result: &mut i32) -> (i32, i32, i32) {
            if root.is_none() {
                return (i32::MAX, i32::MIN, 0);
            }
            let node = root.as_ref().unwrap().borrow();
            let (lmn, lmx, lsum) = dfs(node.left.clone(), result);
            let (rmn, rmx, rsum) = dfs(node.right.clone(), result);
            if lmx < node.val && node.val < rmn {
                (*result) = (*result).max(lsum + rsum + node.val);
                (lmn.min(rmn).min(node.val), lmx.max(rmx).max(node.val), lsum + rsum + node.val)
            } else {
                (i32::MIN, i32::MAX, 0)
            }
        }
        let mut result = 0;
        dfs(root, &mut result);
        result
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