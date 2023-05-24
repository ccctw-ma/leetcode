#[warn(unused_imports)]
use std::cmp::{max, min};
use std::collections::{VecDeque, LinkedList, HashMap, HashSet, BTreeMap, BTreeSet, BinaryHeap};

fn main() {
    let mut heap = BinaryHeap::new();

    heap.push(3);
    heap.push(2);
    heap.push(4);
    heap.push(1);

    // while let Some(x) = heap.pop() {
    //     println!("{}", x);
    // }


    let numbers = [1, 2, 3, 4, 5];

    let zero = "0".to_string();

    let result = numbers.iter().fold(zero, |acc, &x| {
        format!("({acc} + {x})")
    });



    println!("{}", result);
    let i: i32 = 0;
    let j = (i - 1) ;
    println!("{}", j);

    let mut a = Some(23);
    let b = a.take();
    println!("{}", a.is_none());
}
