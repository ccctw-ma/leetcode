#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn frog_position(n: i32, edges: Vec<Vec<i32>>, t: usize, target: usize) -> f64 {
        let (t, target) = (t as usize, target as usize);
        let mut g = vec![vec![]; (n + 1) as usize];
        for edge in edges.iter() {
            let (a, b) = (edge[0] as usize, edge[1] as usize);
            g[a].push(b);
            g[b].push(a);
        }
        g[1].push(0);

        fn dfs(g: &Vec<Vec<usize>>, x: usize, p: usize, time: usize, target: usize) -> usize {
            if time == 0 {
                return if x == target { 1 } else { 0 };
            }
            if x == target {
                return if g[x].len() == 1 { 1 } else { 0 };
            }
            for y in g[x].iter() {
                if *y == p {
                    continue;
                }
                let prod = dfs(g, *y, x, time - 1, target);
                if prod != 0 {
                    return prod * (g[x].len() - 1);
                }
            }
            return 0;
        }
        let prod = dfs(&g, 1, 0, t, target);
        if prod != 0 { 1.0 / prod as f64 } else { 0.0 }
    }
}
// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::arg_parse::ArgParse;
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::frog_position(7, ArgParse::parse_matrix(" [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]"), 2, 4), 0.16666666666666666);
    }
}