#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn rearrange_barcodes(barcodes: Vec<i32>) -> Vec<i32> {
        let mut cnt = HashMap::new();

        let mut res = vec![];
        for bar in barcodes {
            *cnt.entry(bar).or_insert(0) += 1;
        }
        let mut heap = cnt.into_iter().map(|(k, v)| (v, k)).collect::<BinaryHeap<(i32, i32)>>();
        while let Some(mut max0) = heap.pop() {
            res.push(max0.1);
            max0.0 -= 1;
            if let Some(mut max1) = heap.pop() {
                res.push(max1.1);
                max1.0 -= 1;
                if max1.0 >= 0 {
                    heap.push(max1);
                }
            }
            if max0.0 > 0 {
                heap.push(max0);
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
        assert_eq!(Solution::rearrange_barcodes(vec![1, 1, 1, 2, 2, 2]), vec![2, 1, 2, 1, 2, 1, 1]);
    }
}