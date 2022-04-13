const test01 = (s) => {
    let x = 0, y = 0;
    for (let c of [...s]) {
        if (c === 'R') {
            x++;
        } else if (c === 'L') {
            x--;
        } else if (c === 'U') {
            y++;
        } else {
            y--;
        }
    }
    return '(' + x + ',' + y + ')'
}

// n * m 
const test02 = (n, m, k, matrix, stones) => {

    const directions = [[-1, 0], [1, 0], [0, -1], [0, 1]];
    const canTouch = (x, y, tx, ty) => {
        return (Math.abs(tx - x) + Math.abs(ty - y)) === 1
    }
    const search = (n, m, matrix, x, y, tx, ty) => {
        let queue = [[x, y]];
        let count = 0;
        let visited = new Set();
        while (queue) {
            let temp = [];
            for (let i = 0; i < queue.length; i++) {
                let a = queue[i][0], b = queue[i][1];
                visited.add(a * 100 + b);
                if (canTouch(a, b, tx, ty)) {
                    return [count, a, b];
                }
                for (let dir of directions) {
                    let nx = a + dir[0];
                    let ny = b + dir[1];
                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= m && matrix[nx - 1][ny - 1] !== '#' && !visited.has(nx * 100 + ny)) {
                        temp.push([nx, ny])
                    }
                }
            }
            queue = temp;
            count++;
        }
        return [-1, -1, -1];
    }

    let res = 0, sx = 1, sy = 1, ex = 1, ey = 1;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (matrix[i][j] === 'S') {
                sx = i + 1;
                sy = j + 1;
            } else if (matrix[i][j] === 'F') {
                ex = i + 1;
                ey = j + 1;
            }
        }
    }
    for (let stone of stones) {
        let [tx, ty] = stone;
        let step = search(n, m, matrix, sx, sy, tx, ty);
        if (step[0] === -1) {
            return -1;
        } else {
            res += step[0];
        }
        sx = step[1];
        sy = step[2];
    }
    let step = search(n, m, matrix, sx, sy, ex, ey);
    return step[0] === -1 ? -1 : res + step[0] + 1;
}

const test03 = (n, k, arr) => {
    const mod = 1e9 + 7;
    let res = 0;
    let odd = new Array(k + 1).fill(0);
    let even = new Array(k + 1).fill(0);
    for (let i = 0; i < arr.length; i++) {
        let c = arr[i];
        let temp_odd = new Array(k + 1).fill(0);
        let temp_even = new Array(k + 1).fill(0);
        temp_odd[1] = odd[1] + (c % 2 === 0 ? 0 : 1);
        temp_even[1] = even[1] + (c % 2 === 0 ? 1 : 0);
        for (let j = 2; j <= Math.min(i + 1, k); j++) {
            temp_odd[j] = (c % 2 === 0 ? odd[j - 1] : even[j - 1]);
            temp_even[j] = (c % 2 === 0 ? even[j - 1] : odd[j - 1]);
        }
        res = (res + temp_even[k]) % mod;
        odd = temp_odd;
        even = temp_even;
    }
    return res;
}

const test03_plus = (n, k, arr) => {

    const C = (n, m) => {
        if (m === 0 || n === m) return 1;
        let res = 1;
        if (m > n / 2) m = n - m;
        for (let i = 0; i < m; i++) res *= (n - i);
        for (let i = 1; i <= m; i++) res /= i;
        return res;
    }
    let odd = 0, even = 0, res = 0;
    for (let num of arr) {
        if (num % 2 === 0) {
            even++;
        } else {
            odd++;
        }
    }
    const mod = 1e9 + 7;
    for (let i = 0; i <= Math.min(odd, k); i += 2) {
        if (k - i <= even) {
            res += C(odd, i) * C(even, k - i) % mod;
        }
    }
    return res;
}
let matrix = [
    "#....", "####.", "FS..."
]
// console.log(test02(3, 5, 3, matrix, [[1, 1], [2, 3], [2, 2]]))
console.log(test03_plus(10, 2, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))


const C = (n, m) => {
    if (m === 0 || n === m) return 1;
    let res = 1;
    if (m > n / 2) m = n - m;
    for (let i = 0; i < m; i++) res *= (n - i);
    for (let i = 1; i <= m; i++) res /= i;
    return res;
}

// console.log(C(99, 50))