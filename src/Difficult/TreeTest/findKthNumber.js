/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
const findKthNumber = function (n, k) {
    const getSteps = (cur, n) => {
        let step = 0, left = cur, right = cur;
        while (left <= n) {
            step += (Math.min(n, right) - left) + 1;
            left *= 10;
            right = right * 10 + 9;
        }
        return step;
    }
    k -= 1;
    let cur = 1;
    while (k) {
        let step = getSteps(cur, n);
        if (step <= k) {
            cur += 1;
            k -= step
        } else {
            cur *= 10;
            k -= 1;
        }
    }
    return cur
};