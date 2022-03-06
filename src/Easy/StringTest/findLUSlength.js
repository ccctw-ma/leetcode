/**
 * @param {string} a
 * @param {string}b
 * @return {number}
 */
const findLUSlength = (a,b) => {
    let l1 = a.length, l2 = b.length;
    if(l1!==l2) return  Math.max(l1,l2);
    return -1;
}