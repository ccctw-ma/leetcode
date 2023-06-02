#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn refcell_test() -> i32 {
        let mut x = 5;
        let _y = &mut x;

        let mut a = Some(2);
        let b = a.take();
        println!("{:?}, {:?}", a, b);

        1
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::refcell_test(), 1);
    }
}