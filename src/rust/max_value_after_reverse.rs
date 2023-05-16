#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}


impl Solution {
    pub fn max_value_after_reverse(nums: Vec<i32>) -> i32 {
        let (mut base, mut d) = (0, 0);
        let (mut mx, mut mn) = (i32::MIN, i32::MAX);
        for i in 0..nums.len() - 1 {
            let (a, b) = (nums[i], nums[i + 1]);
            base += abs(a - b);
            mx = max(mx, min(a, b));
            mn = min(mn, max(a, b));
            d = max(d, max(abs(nums[0] - b) - abs(a - b), abs(nums[nums.len() - 1] - a) - abs(a - b)));
        }
        base + max(d, 2 * (mx - mn))
    }
}

fn abs(x: i32) -> i32 {
    x.abs()
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::max_value_after_reverse(vec![2, 4, 9, 24, 2, 1, 10]), 68);
    }
}