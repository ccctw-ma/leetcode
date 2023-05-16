#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let mut buc = HashSet::<i32>::new();
        for c in nums.iter() {
            buc.insert(*c);
        }
        let mut res = -1;
        for c in nums {
            if c > 0 && buc.contains(&(-c)) {
                res = res.max(c);
            }
        }
        res
    }
    #[allow(unused)]
    pub fn find_max_k2(nums: Vec<i32>) -> i32 {
        let mut st = HashSet::new();
        nums.into_iter().fold(-1, |mut res, x| {
            if st.contains(&(-x)) {
                res = res.max(x.abs());
            }
            st.insert(x);
            res
        })
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::find_max_k(vec![-1, 2, -3, 3]), 3);
    }
}