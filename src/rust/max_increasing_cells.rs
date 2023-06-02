#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn max_increasing_cells(mat: Vec<Vec<i32>>) -> i32 {
        let mut buc = std::collections::BTreeMap::new();
        let (m, n) = (mat.len(), mat[0].len());
        for i in 0..m {
            for j in 0..n {
                buc.entry(mat[i][j]).or_insert(vec![]).push((i, j));
            }
        }
        let mut res: usize = 0;
        let mut rows: Vec<usize> = vec![0; m];
        let mut cols: Vec<usize> = vec![0; n];
        for (_, vs) in buc {
            let tmp: Vec<usize> = vs.iter().map(|(x, y)| rows[*x].max(cols[*y]) + 1).collect();
            res = res.max(*tmp.iter().max().unwrap());
            for ((x, y), f)in vs.iter().zip(tmp.iter()){
                rows[*x] = rows[*x].max(*f);
                cols[*y] = cols[*y].max(*f);
            }
        }
        res as i32
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::arg_parse::ArgParse;
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::max_increasing_cells(ArgParse::parse_matrix("[[3,1,6],[-9,5,7]]")), 4);
    }
}