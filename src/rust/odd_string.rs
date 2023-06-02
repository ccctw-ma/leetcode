#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn odd_string(words: Vec<String>) -> String {
        let mut buc = std::collections::HashMap::new();
        for s in words.into_iter() {
            let k = s.as_bytes().windows(2).map(|v| (v[1] as i32 - v[0] as i32).to_string() + ",").collect::<String>();
            buc.entry(k).or_insert(vec![]).push(s);
        }
        buc.values().find(|v| v.len() == 1).unwrap()[0].to_owned()


        // for word in words.iter() {
        //     let mut s = String::from("");
        //     word.chars().zip(word.chars().skip(1)).for_each(|(a, b)| {
        //         let c = (a as i32 - b as i32).to_string();
        //         s.push_str(c.as_str());
        //     });
        //     buc.entry(s.clone()).or_insert(vec![]).push(word);
        // }
        //
        //
        // for (_, v) in buc {
        //     if v.len() == 1 {
        //         return v[0].to_string();
        //     }
        // }
        //
        //
        // String::from("")
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::odd_string(vec![String::from("adc"), String::from("wzy"), String::from("abc")]), String::from("abc"));
    }
}