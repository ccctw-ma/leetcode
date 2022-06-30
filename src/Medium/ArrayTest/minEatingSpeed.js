/**
 * @Author: msc
 * @Date: 2022-06-07 10:59:58
 * @LastEditTime: 2022-06-07 11:52:51
 * @LastEditors: msc
 * @Description: 
 */
/**
 * @param {number[]} piles
 * @param {number} h
 * @return {number}
 */
var minEatingSpeed = function (piles, h) {
    piles.sort((a, b) => a - b);
    let sum_piles = piles.reduce((pre, cur) => pre + cur);
    // console.log(sum_piles);
    let n = piles.length;
    let l = Math.ceil(sum_piles / h), r = piles[n - 1];
    const check = (k) => {
        let l = 0, r = n;
        while (l < r) {
            let mid = ~~((l + r) / 2);
            if (k < piles[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // console.log(l);
        let count = l;
        for (let i = l; i < n; i++) {
            count += Math.ceil(piles[i] / k);
        }
        return count <= h;
    }
    while (l < r) {
        let mid = ~~((l + r) / 2);
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};

console.log(minEatingSpeed([3, 6, 7, 11], 8));