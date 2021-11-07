/**
 * @param {string[]} words
 * @return {string[]}
 */
var findWords = function (words) {
    let bucket1 = new Set();
    let bucket2 = new Set();
    let bucket3 = new Set();
    "qwertyuiop".split("").forEach(c => bucket1.add(c));
    "asdfghjkl".split("").forEach(c => bucket2.add(c));
    "zxcvbnm".split("").forEach(c => bucket3.add(c));
    let res = [];
    words.forEach(temp => {
        let word = temp.toLowerCase();
        let c = word[0];
        let flag = true;
        if (bucket1.has(c)) {
            for (let i = 1; i < word.length; i++) {
                if (!bucket1.has(word[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.push(temp);
            }
        } else if (bucket2.has(c)) {
            for (let i = 1; i < word.length; i++) {
                if (!bucket2.has(word[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.push(temp);
            }
        } else {
            for (let i = 1; i < word.length; i++) {
                if (!bucket3.has(word[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.push(temp);
            }
        }
    })
    return res;
};


/**
 * @param {string[]} words
 * @return {string[]}
 */
var findWords2 = function (words) {
    return words.filter(item => {
        const wordArr = [...new Set(item.toLocaleLowerCase())]
        return [
            wordArr.every(item => 'qwertyuiop'.indexOf(item) > -1),
            wordArr.every(item => 'asdfghjkl'.indexOf(item) > -1),
            wordArr.every(item => 'zxcvbnm'.indexOf(item) > -1)
        ].some(item => item)
    })
};


// console.log([...'adsdf'].every(item=>'asdfghjkl'.indexOf(item) > -1))
// console.log([1, 2, 3].some(item => item > 2))
// console.log(findWords2(["adsdf","sfd"]))


// console.log(Array.from({length: 10}, () => 1))