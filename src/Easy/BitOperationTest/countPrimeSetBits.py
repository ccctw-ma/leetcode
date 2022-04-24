from collections import Counter


class Solution:
    def countPrimeSetBits(self, left: int, right: int) -> int:
        res = 0
        for i in range(left, right + 1):
            res += ()
        return sum((665772 >> bin(i).count('1')) & 1 for i in range(left, right + 1))


if __name__ == '__main__':
    print(bin(179382).count('1'))
