#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn add_negabinary(mut arr1: Vec<i32>, mut arr2: Vec<i32>) -> Vec<i32> {
        arr1.reverse();
        arr2.reverse();
        let mut j = 0;
        for i in 0..arr1.len().max(arr2.len()) + 2 {
            let mut t = if i < arr1.len() { arr1[i] } else { 0 };
            t += if i < arr2.len() { arr2[i] } else { 0 };
            t += j;
            match t {
                -1 => {
                    t = 1;
                    j = 1
                }
                0 => {
                    t = 0;
                    j = 0;
                }
                1 => {
                    t = 1;
                    j = 0;
                }
                2 => {
                    t = 0;
                    j = -1
                }
                3 => {
                    t = 1;
                    j = -1
                }
                _ => unreachable!(),
            }
            if arr1.len() == i {
                arr1.push(t);
            } else {
                arr1[i] = t;
            }
        }
        while let [_, .., 0] = arr1[..] {
            arr1.pop();
        }
        // while arr1.len() > 1 && arr1[arr1.len() - 1] == 0 {
        //     arr1.pop();
        // }
        arr1.reverse();
        arr1
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::add_negabinary(vec![1, 1, 1, 1, 1], vec![1, 0, 1]), vec![1, 0, 0, 0, 0]);
    }
}