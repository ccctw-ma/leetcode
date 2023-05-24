#[allow(unused)]
pub struct ArgParse;


#[allow(unused)]
impl ArgParse {
    pub fn parse_vec(s: &str) -> Vec<i32> {
        let mut res: Vec<i32> = vec![];
        let arr: Vec<&str> = s[1..s.len() - 1].trim().split(",").collect();
        for item in arr.into_iter() {
            let num = item.trim().parse().unwrap();
            res.push(num);
        }
        res
    }

    pub fn parse_matrix(s: &str) -> Vec<Vec<i32>> {
        serde_json::from_str(s).unwrap()
    }
}

// submission codes end

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        let v1 = vec![1, 2, 3, 4];
        let v2 = vec![vec![1, 2], vec![3, 4]];
        assert_eq!(ArgParse::parse_vec("[1, 2, 3, 4]"), v1);
        assert_eq!(ArgParse::parse_matrix("[[1, 2], [3, 4]]"), v2);
    }
}