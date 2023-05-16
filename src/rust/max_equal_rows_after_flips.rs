#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

// impl Solution {
//     pub fn max_equal_rows_after_flips(mut matrix: Vec<Vec<i32>>) -> i32 {
//         let (m, n) = (matrix.len(), matrix[0].len());
//         let mut cnt = std::collections::HashMap::new();
//         for row in matrix.iter_mut() {
//             if row[0] == 1 {
//                 for j in row.iter_mut() {
//                     *j ^= 1;
//                 }
//             }
//             let mut base = String::new();
//             for j in row {
//                 base.push_str(j.to_string().as_str())
//             }
//             *cnt.entry(base).or_insert(0) += 1;
//         }
//         match cnt.values().max() {
//             Some(x) => *x,
//             _ => 0
//         }
//     }
// }
fn to_tuple(x: &Vec<i32>) -> ((u128, u128, u128), (u128, u128, u128)) {
    let mut res1 = [0u128; 3];
    let mut res2 = [0u128; 3];
    for (i, &t) in x.iter().enumerate() {
        res1[i / 128] |= (t as u128) << (i % 128);
        res2[i / 128] |= (t as u128 ^ 1) << (i % 128);
    }
    return ((res1[0], res1[1], res1[2]), (res2[0], res2[1], res2[2]));
}

impl Solution {
    pub fn max_equal_rows_after_flips(matrix: Vec<Vec<i32>>) -> i32 {
        let n = matrix.len();
        let m = matrix[0].len();
        let mut hm = std::collections::HashMap::new();
        for v in &matrix {
            let t = to_tuple(v);
            *hm.entry(t.0).or_insert(0) += 1;
            *hm.entry(t.1).or_insert(0) += 1;
        }
        // *hm.values().into_iter().max().unwrap()
        hm.into_values().max().unwrap()
    }
}
// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::max_equal_rows_after_flips(vec![
            vec![0, 0, 0],
            vec![0, 0, 1],
            vec![1, 1, 0],
        ]), 2);
    }
}