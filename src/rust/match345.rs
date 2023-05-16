#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

impl Solution {
    pub fn circular_game_losers(n: i32, k: i32) -> Vec<i32> {
        let mut base = 0;
        let mut vis = std::collections::HashSet::from([base]);
        let mut step = 1;
        loop {
            let next = (base + step * k) % n;
            if vis.contains(&next) {
                break;
            }
            vis.insert(next);
            step += 1;
            base = next;
        }
        let mut res = vec![];
        for i in 0..n {
            if !vis.contains(&i) {
                res.push(i + 1);
            }
        }
        res
    }

    pub fn does_valid_array_exist(derived: Vec<i32>) -> bool {
        derived.iter().fold(0, std::ops::BitXor::bitxor) == 0
    }

    pub fn max_moves(grid: Vec<Vec<i32>>) -> i32 {
        // fn dfs(x: usize, y: usize, grid: &Vec<Vec<i32>>, cache: &mut Vec<Vec<i32>>) -> i32 {
        //     if cache[x][y] != -1 {
        //         return cache[x][y];
        //     }
        //     let mut res = 0;
        //     let (m, n) = (grid.len() as i32, grid[0].len() as i32);
        //     for (dx, dy) in [(-1, 1), (0, 1), (1, 1)] {
        //         let (nx, ny) = (x as i32 + dx, y as i32 + dy);
        //         if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx as usize][ny as usize] > grid[x][y] {
        //             res = res.max(dfs(nx as usize, ny as usize, grid, cache));
        //         }
        //     }
        //     let ans = res + 1;
        //     cache[x][y] = ans;
        //     ans
        // }
        // let (m, n) = (grid.len(), grid[0].len());
        // let mut cache = vec![vec![-1; n]; m];
        // let mut max_res = 0;
        // for i in 0..m {
        //     max_res = max_res.max(dfs(i, 0, &grid, &mut cache));
        // }
        //
        // max_res - 1
        let m = grid.len();
        let n = grid[0].len();
        static DIR: [(i32, i32); 3] = [(-1, 1), (0, 1), (1, 1)];
        let mut dp = vec![vec![0; n]; m];
        for j in (0..n - 1).rev() {
            for i in 0..m {
                for (dx, dy) in DIR {
                    let (nx, ny) = ((i as i32 + dx) as usize, (j as i32 + dy) as usize);
                    // println!("{} {}", nx, ny);
                    if nx < m && ny < n {
                        if grid[nx][ny] > grid[i][j] {
                            dp[i][j] = dp[i][j].max(dp[nx][ny] + 1);
                        }
                    }
                }
            }
        }
        dp.iter().map(|x| x[0]).max().unwrap()
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::circular_game_losers(5, 2), vec![4, 5]);
        assert_eq!(Solution::does_valid_array_exist(vec![1, 1, 0]), true);
        assert_eq!(Solution::max_moves(vec![
            vec![2, 4, 3, 5],
            vec![5, 4, 9, 3],
            vec![3, 4, 2, 11],
            vec![10, 9, 13, 15],
        ]), 3);
    }
}