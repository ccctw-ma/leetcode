const test01 = (M, strategy, cpu, mem, cpu_arch, NP, matrix) => {

    let res = [];
    matrix.forEach(arr => {
        let [index, _cpu, _mem, _cpu_arch, _np] = arr;
        if (_cpu >= cpu && _mem >= mem && (_cpu_arch === 9 || cpu_arch === 9 || _cpu_arch === cpu_arch) && (_np === 2 || NP === 2 || _np === NP)) {
            res.push(arr);
        }
    })
    if (strategy === 1) {
        res.sort((a, b) => {
            if (a[1] !== b[1]) {
                return a[1] - b[1];
            } else if (a[2] !== b[2]) {
                return a[2] - b[2];
            } else {
                return a[0] - b[0];
            }
        })
    } else {
        res.sort((a, b) => {
            if (a[2] !== b[2]) {
                return a[2] - b[2];
            } else if (a[1] !== b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        })
    }


    let count = Math.min(res.length, M);
    if (count === 0) {
        console.log(0)
    }
    let ans = [];
    for (let i = 0; i < count; i++) {
        ans.push(res[i][0]);
    }
    console.log(ans)
}

const test03 = (n, arr) => {
    let sum = arr.reduce((pre, cur) => {
        pre += cur;
        return pre;
    }, 0);
    //不能分配
    if (sum % 2 !== 0) {
        return -1;
    }
    const half = sum / 2;
    let dp = new Array(half + 1).fill(0);
    for (let num in arr) {
        dp[num] += 1;
        for (let j = half; j > num; j--) {
            if (dp[j - num]) {
                dp[j]++;
            }
        }
    }
    // if (dp[half]) {
    //     let res1 = [], res2 = [];
    //     arr.sort((a, b) => b - a);
    //     let count =
    //     for (let num of arr) {
    //         if (num)
    //             }
    //     return -1;
    // }

}


// test01(3, 2, 3, 200, 9, 2, [[0, 2, 200, 0, 1], [1, 4, 330, 2, 1], [2, 3, 400, 3, 1], [3, 3, 310, 1, 1], [4, 3, 320, 8, 1], [5, 3, 330, 0, 1]])
// test01(3, 1, 3, 200, 0, 1, [[0, 2, 200, 0, 1], [1, 3, 400, 0, 1], [2, 3, 400, 1, 0], [3, 3, 300, 0, 1]])


data = {
    a: '2',
    b: ' 1',
    c: "hellow"
}


// console.log(data)
// for(let a of Object.keys(data)){
//     console.log(a)
//     data[a] = Number.parseInt(data[a])
// }
// console.log(data)


const parseString = (s) => {
    const part = /\d+\.\d+/g
    let index = s.search(part);
    return Number.parseFloat(s.substring(index))
}
console.log(parseString("华北地区1.23"))


