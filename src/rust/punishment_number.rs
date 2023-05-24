#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};


pub struct Solution {}

impl Solution {
    pub fn punishment_number(n: i32) -> i32 {
        fn tb(cs: &Vec<u32>, idx: usize, pre: i32, tar: i32) -> bool {
            if idx == cs.len() {
                return pre == tar;
            }

            let mut x = 0;
            for j in idx..cs.len() {
                x = x * 10 + (cs[j] as i32);
                if x > tar {
                    break;
                }
                if tb(cs, j + 1, pre + x, tar) {
                    return true;
                }
            }
            false
        }

        let mut res = 1;
        for i in 2..=n {
            let s: Vec<u32> = (i * i).to_string().chars().map(|c| c.to_digit(10).unwrap()).collect();

            if tb(&s, 0, 0, i) {
                res += i * i;
            }
        }
        res
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::punishment_number(10), 182);
        assert_eq!(Solution::punishment_number(37), 1478);
    }
}