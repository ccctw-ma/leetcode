#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn have_conflict(event1: Vec<String>, event2: Vec<String>) -> bool {
        let (s1, e1) = (event1[0].as_str(), event1[1].as_str());
        let (s2, e2) = (event2[0].as_str(), event2[1].as_str());
        !(e1 < s2 || e2 < s1)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(
            Solution::have_conflict(vec![String::from("01:15"), String::from("02:00")],
                                    vec![String::from("02:00"), String::from("03:00")],
            ), true
        );
    }
}