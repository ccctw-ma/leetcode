/**
 * @param {string[]} queries
 * @param {string} pattern
 * @return {boolean[]}
 */
var camelMatch = function (queries, pattern) {
    let reg = '^[a-z]*'
    for (const p of pattern) {
        reg += `${p}[a-z]*`
    }
    reg += '$'
    reg = new RegExp(reg)
    console.log(reg)
    return queries.map(q => reg.test(q))
};


// const reg = new RegExp(`^[a-z]*F[a-z]*B[a-z]*$`)
// console.log(reg)
// console.log(reg.exec("FooBar"))

console.log(camelMatch(queries = ["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], pattern = "FB"))
console.log(camelMatch(queries = ["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], pattern = "FoBaT"))

