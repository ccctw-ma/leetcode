#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn largest_vals_from_labels(values: Vec<i32>, labels: Vec<i32>, num_wanted: i32, use_limit: i32) -> i32 {
        let mut buc = std::collections::HashMap::new();
        for (v, l) in values.into_iter().zip(labels.into_iter()) {
            buc.entry(l).or_insert(vec![]).push(v);
        }
        let mut arr: Vec<i32> = vec![];
        buc.values_mut().for_each(|vs| {
            vs.sort_by(|a, b| b.cmp(a));
            arr.extend(vs.iter().take(use_limit as usize));
        });
        arr.sort_by(|a, b| b.cmp(a));
        arr.into_iter().take(num_wanted as usize).sum()
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::largest_vals_from_labels(
            vec![9, 8, 8, 7, 6],
            vec![0, 0, 0, 1, 1],
            3,
            1,
        ), 16);
    }
}