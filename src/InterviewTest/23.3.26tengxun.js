// 本题为考试多行输入输出规范示例，无需提交，不计分。
const rl = require("readline").createInterface({input: process.stdin});
var iter = rl[Symbol.asyncIterator]();
const readline = async () => (await iter.next()).value;

// void async function () {
//     // Write your code here
//     // var n = parseInt(await readline());
//     // var ans = 0;
//     // for (var i = 0; i < n; i++) {
//     //     lines = (await readline()).split(" ");
//     //     for (var j = 0; j < lines.length; j++) {
//     //         ans += parseInt(lines[j]);
//     //     }
//     // }
//     // console.log(ans);
//     let a = await readline()
//     let b = await readline()
//     let arr1 = a.split("").reverse().map(x => parseInt(x))
//     let arr2 = b.split("").reverse().map(x => parseInt(x))
//     let m = arr1.length, n = arr2.length;
//     let flag = false;
//     for (let i = 0; i < n; i++) {
//         let temp = false;
//         if (flag) {
//             arr1[i] -= 1
//         }
//         if (arr1[i] >= arr2[i]) {
//             arr1[i] -= arr2[i]
//         } else {
//             temp = true;
//             arr1[i] = arr1[i] + 10 - arr2[i]
//         }
//         flag = temp;
//     }
//
//     for (let i = n; flag && i < m; i++) {
//         arr1[i] -= 1;
//         if (arr1[i] < 0) {
//             arr1[i] += 10
//         } else {
//             flag = false
//         }
//     }
//     let idx = m - 1;
//     while (idx >= 0 && arr1[idx] === 0) idx -= 1
//     let res = []
//     while (idx >= 0) {
//         res.push(arr1[idx])
//         idx -= 1
//     }
//     console.log(res.join(""))
// }()

void async function () {

    let format = await readline()
    let stamp = parseInt(await readline())
    let date = new Date(stamp)
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const buc = {
        YYYY: year.toString(),
        YY: year.toString().substring(2),
        MM: month >= 10 ? month.toString() : '0' + month,
        M: month.toString(),
        DD: day >= 10 ? day.toString() : "0" + day,
        D: day.toString()
    }
    for (let [k, v] of Object.entries(buc)) {
        format = format.replace(k, v)
    }
    console.log(format)
}()