#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let (sum, cnt) = nums.iter().filter(|x| *x % 6 == 0).fold((0, 0), |(sum, cnt), &x| (sum + x, cnt + 1));
        if cnt == 0 { 0 } else { sum / cnt }
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::arg_parse::ArgParse;
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::average_value(ArgParse::parse_vec("[1,3,6,10,12,15]")), 9);
    }
}