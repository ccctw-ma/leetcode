#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
use std::usize;

pub struct Solution {}

impl Solution {
    pub fn shortest_path_binary_matrix(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        if grid[0][0] == 1 || grid[n - 1][n - 1] == 1 {
            return -1;
        }
        let mut vis = vec![vec![usize::MAX; n]; n];
        let dirs: [[i32; 2]; 8] = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]];
        vis[0][0] = 1;
        let mut q: VecDeque<(usize, usize, usize)> = std::collections::VecDeque::new();
        q.push_back((1, 0, 0));
        while q.len() != 0 {
            if let Some(tmp) = q.pop_front() {
                let (step, x, y) = tmp;
                if x == n - 1 && y == n - 1 {
                    return step as i32;
                }
                for dir in dirs.iter() {
                    let (dx, dy) = (dir[0], dir[1]);
                    let (nx, ny) = ((x as i32 + dx) as usize, (y as i32 + dy) as usize);
                    if nx < n && ny < n && grid[nx][ny] == 0 && step + 1 < vis[nx][ny] {
                        vis[nx][ny] = step + 1;
                        q.push_back((step + 1, nx, ny))
                    }
                }
            }
        }

        -1
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::arg_parse::ArgParse;
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::shortest_path_binary_matrix(ArgParse::parse_matrix("[[0,1,1,1,1,1,1,1],[0,1,1,0,0,0,0,0],[0,1,0,1,1,1,1,0],[0,1,0,1,1,1,1,0],[0,1,1,0,0,1,1,0],[0,1,1,1,1,0,1,0],[0,0,0,0,0,1,1,0],[1,1,1,1,1,1,1,0]]
")), 25);
    }
}