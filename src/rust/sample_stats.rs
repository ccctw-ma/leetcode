#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

pub struct Solution {}

// impl Solution {
//     pub fn sample_stats(count: Vec<i32>) -> Vec<f64> {
//         let tot: i32 = count.iter().sum();
//         let (mut mn, mut mx, mut median, mut mode) = (256, 0, 0, 0);
//         let (mut cnt, mut s, mut mc) = (0, 0.0, 0);
//         let (l, r) = (((tot + 1) as f64 / 2.0).floor() as usize, ((tot + 2) as f64 / 2.0).floor() as usize);
//
//         for (i, c) in count.into_iter().enumerate() {
//             match c {
//                 0 => {}
//                 _ => {
//                     let c = c as usize;
//                     mx = i;
//                     mn = if mn == 256 { i } else { mn };
//                     mode = if c > mc {
//                         mc = c;
//                         i
//                     } else { mode };
//                     s += (i * c) as f64;
//                     if cnt < l && cnt + c >= l {
//                         median += i;
//                     }
//                     if cnt < r && cnt + c >= r {
//                         median += i;
//                     }
//                     cnt += c;
//                 }
//             }
//         }
//
//         vec![mn as f64, mx as f64, (s as f64 / tot as f64), (median as f64 / 2.0), mode as f64]
//     }
// }


impl Solution {
    pub fn sample_stats(count: Vec<i32>) -> Vec<f64> {
        vec![
            Solution::min(&count),
            Solution::max(&count),
            Solution::mean(&count),
            Solution::median(&count),
            Solution::mode(&count),
        ]
    }

    pub fn min(count: &Vec<i32>) -> f64 {
        (0..=255).find(|i| count[*i] > 0).unwrap() as f64
    }

    pub fn max(count: &Vec<i32>) -> f64 {
        (0..=255).rfind(|i| count[*i] > 0).unwrap() as f64
    }

    pub fn mean(count: &Vec<i32>) -> f64 {
        let (sum, cnt) = count.iter()
            .enumerate()
            .fold((0f64, 0f64), |(sum, cnt), (i, x)| (sum + i as f64 * *x as f64, cnt + *x as f64));
        sum / cnt
    }

    pub fn median(count: &Vec<i32>) -> f64 {
        let cnt = count.iter().map(|x| *x as i64).sum::<i64>();  // 总的样本个数
        let k = cnt / 2 + 1;  // 中位数的位置，cnt为偶数则是两个中位数中后一个的位置
        let mut pre = 0; // 目前已经遍历了多少个数了
        let mut res = 0f64;
        for i in 0..=255 {
            pre += count[i] as i64;
            if pre >= k { // 如果遍历的数量大于等于中位数的位置，则该数是其中一个中位数
                res = i as f64;  // 如果不是第i组的第一个数，两个中位数相等（结合单个中位数的情况）
                if cnt % 2 == 0 && pre - count[i] as i64 + 1 == k {  // 如果是第i组的第一个数，则往左找前一个中位数
                    res = (i as f64 + (0..i).rfind(|k| count[*k] > 0).unwrap() as f64) / 2.0;
                }
                break;
            }
        }
        res
    }

    pub fn mode(count: &Vec<i32>) -> f64 {
        (0..=255).max_by_key(|i| count[*i]).unwrap() as f64
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use crate::rust::utils::arg_parse::ArgParse;
    use super::*;

    #[test]
    fn test() {
        assert_eq!(Solution::sample_stats(ArgParse::parse_vec("[264,912,1416,1903,2515,3080,3598,4099,4757,5270,5748,6451,7074,7367,7847,8653,9318,9601,10481,10787,11563,11869,12278,12939,13737,13909,14621,15264,15833,16562,17135,17491,17982,18731,19127,19579,20524,20941,21347,21800,22342,21567,21063,20683,20204,19818,19351,18971,18496,17974,17677,17034,16701,16223,15671,15167,14718,14552,14061,13448,13199,12539,12265,11912,10931,10947,10516,10177,9582,9102,8699,8091,7864,7330,6915,6492,6013,5513,5140,4701,4111,3725,3321,2947,2357,1988,1535,1124,664,206,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]")
        ), vec![0.0, 89.0, 42.826829, 42.0, 40.0]);
    }
}