#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn num_tile_possibilities(tiles: String) -> i32 {
        let mut comb = vec![vec![0; 8]; 8];
        for i in 0..8 {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for j in 1..i {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }

        let mut cnt = std::collections::HashMap::new();
        let n = tiles.len();
        for c in tiles.to_string().chars() {
            *cnt.entry(c).or_insert(0) += 1;
        }
        let m = cnt.len();
        let mut dp = vec![vec![0; n + 1]; m + 1];
        dp[0][0] = 1;
        let mut i = 1;
        for cnt in cnt.values(){
            for j in 0..n + 1 {
                let mut k = 0;
                while k <= j && k <= *cnt {
                    dp[i][j] += dp[i - 1][j - k] * comb[j][k];
                    k += 1;
                }
            }
            i += 1;
        }
        let mut res = 0;
        for j in 1..=n {
            res += dp[m][j];
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
        assert_eq!(Solution::num_tile_possibilities(String::from("AAB")), 8);
    }
}