#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn mct_from_leaf_values(arr: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut stk: Vec<i32> = vec![i32::MAX];
        for x in arr {
            while stk.last().unwrap() <= &x {
                let mid = stk.pop().unwrap();
                res += mid * stk.last().unwrap().min(&x);
            }
            stk.push(x);
        }
        while stk.len() > 2 {
            let tmp = stk.pop().unwrap();
            res += tmp * stk.last().unwrap()
        }
        res
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::arg_parse::ArgParse;
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::mct_from_leaf_values(ArgParse::parse_vec("[6,2,4]")), 32);
    }
}