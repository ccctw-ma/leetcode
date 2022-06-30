/**
 * @param {number[][]} rects
 */
var Solution = function (rects) {
    this.rects = rects;
    this.arr = [0];
    this.rects.forEach((e, i) => {
        const [a, b, c, d] = e;
        let points = (d - b + 1) * (c - a + 1);
        this.arr.push(this.arr[i] + points);
    })

};

/**
 * @return {number[]}
 */
Solution.prototype.pick = function () {
    let tar = Math.floor(Math.random() * this.arr[this.arr.length - 1]);
    let search = tar + 1;
    let l = 0, r = this.arr.length - 1;
    while (l <= r) {
        let mid = Math.floor((r - l) / 2) + l;
        if (this.arr[mid] === search) {
            l = mid;
            break;
        } else if (this.arr[mid] < search) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    const index = l - 1;
    const [a, b, , d] = this.rects[index];
    tar -= this.arr[index];
    const col = d - b + 1;
    const row = Math.floor(tar / col);
    const co = tar - col * row;
    return [a + row, b + co];
};

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(rects)
 * var param_1 = obj.pick()
 */

let solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
console.log(solution)
// console.log(solution.pick())

let {bar: b, foo: f} = {'bar': "Hello", foo: "World"};
console.log(b, f);
