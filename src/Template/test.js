// let a = "100000"
// let b = "99999"
//
// let arr1 = a.split("").reverse().map(x => parseInt(x))
// let arr2 = b.split("").reverse().map(x => parseInt(x))
// let m = arr1.length, n = arr2.length;
// let flag = false;
// for (let i = 0; i < n; i++) {
//     let temp = false;
//     if (flag) {
//         arr1[i] -= 1
//     }
//     if (arr1[i] >= arr2[i]) {
//         arr1[i] -= arr2[i]
//     } else {
//         temp = true;
//         arr1[i] = arr1[i] + 10 - arr2[i]
//     }
//     flag = temp;
// }
//
// for (let i = n; flag && i < m; i++) {
//     arr1[i] -= 1;
//     if (arr1[i] < 0) {
//         arr1[i] += 10
//     } else {
//         flag = false
//     }
// }
// let idx = m - 1;
// while (idx >= 0 && arr1[idx] === 0) idx -= 1
// let res = []
// while (idx >= 0) {
//     res.push(arr1[idx])
//     idx -= 1
// }
// console.log(res.join(""))


// let format = "YYYY-MM-DD"
// let stamp = parseInt("1642064229723")
let format = "[Month:]M [Day:]D"
let stamp = parseInt("1642065668340")
console.log(format, stamp)
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
    let n = k.length
    for (let i = 0; i < format.length; i++) {
        if (format.substring(i, i + n) === k && (i > 0 && (format[i - 1] !== '['))) {
            format = format.substring(0, i) + v + format.substring(i + n)
        }
    }
}
let res = ''
for (let c of format) {
    if (c !== '[' && c !== ']') {
        res += c
    }
}
console.log(res)


let arr = [
    {t: "1"}, {t: "4"}, {t: "3"}
]
arr.sort((a, b) => a.t - b.t)
console.log(arr)