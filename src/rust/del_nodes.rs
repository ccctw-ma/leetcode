#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
#[allow(unused)]
pub struct Solution {}

use std::rc::Rc;
use std::cell::RefCell;
use crate::rust::utils::tree::TreeNode;
#[allow(unused)]
impl Solution {
    pub fn del_nodes(root: Option<Rc<RefCell<TreeNode>>>, to_delete: Vec<i32>) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let to_delete = to_delete.into_iter().collect::<HashSet<_>>();

        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, to_delete: &HashSet<i32>, res: &mut Vec<Option<Rc<RefCell<TreeNode>>>>) -> Option<Rc<RefCell<TreeNode>>> {
            if let Some(node) = root {
                let mut tr = node.borrow_mut();
                tr.left = dfs(tr.left.take(), to_delete, res);
                tr.right = dfs(tr.right.take(), to_delete, res);

                if to_delete.contains(&tr.val) {
                    if let Some(left) = tr.left.take() {
                        res.push(Some(left));
                    }
                    if let Some(right) = tr.right.take() {
                        res.push(Some(right));
                    }
                    None
                } else {
                    Some(Rc::clone(&node))
                }
            } else {
                None

            }
        }

        let mut res = Vec::new();
        dfs(root.clone(), &to_delete, &mut res);

        let tr = root.clone();
        if !to_delete.contains(&tr.unwrap().borrow_mut().val) {
            res.push(root);
        }
        res
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    #[allow(unused)]
    use super::*;

    #[test]
    fn test() {
        assert_eq!(1, 1);
    }
}