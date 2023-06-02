use std::borrow::Cow;
#[allow(unused)]
use std::cmp::{max, min};
#[allow(unused)]
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};
use std::ffi::{c_char, CStr};
use std::mem::size_of;

pub struct Solution {}

static B: [u8; 10] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
static C: [u8; 11] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0];

impl Solution {
    pub fn test() {
        let a: usize = 42;
        let b: Box<[u8]> = Box::new(B);
        let c: &[u8; 11] = &C;

        println!("a (unsigned 整数):");
        println!("  地址：  {:p}", &a);
        println!("  大小:   {:?} bytes", size_of::<usize>());
        println!("  值:     {:?}\n", a);

        println!("b (B 装在 Box 里):");
        println!("  地址：  {:p}", &b);
        println!("  大小:   {:?} bytes", size_of::<Box<[u8]>>());
        println!("  指向:     {:p}\n", b);

        println!("c (C的引用):");
        println!("  地址：  {:p}", &c);
        println!("  大小:   {:?} bytes", size_of::<&[u8; 11]>());
        println!("  值:     {:p}\n", c);

        println!("B (10bytes 的数组):");
        println!("  地址：  {:p}", &B);
        println!("  大小:   {:?} bytes", size_of::<[u8; 10]>());
        println!("  值:     {:?}\n", B);

        println!("c (11bytes的数字):");
        println!("  地址：  {:p}", &C);
        println!("  大小:   {:?} bytes", size_of::<[u8; 11]>());
        println!("  值:     {:?}\n", C);

        let a: i32 = 42;
        let b: String;
        let c: Cow<str>;
        unsafe {
            let b_ptr = &B as *const u8 as *mut u8;
            b = String::from_raw_parts(b_ptr, 10, 10);
            let c_ptr = &C as *const u8 as *const c_char;
            c = CStr::from_ptr(c_ptr).to_string_lossy();
        }
        print!("a: {}, b: {}, c: {}", a, b, c);
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        Solution::test();
        assert_eq!(1, 1);
    }
}