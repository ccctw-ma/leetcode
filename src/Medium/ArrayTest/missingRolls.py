from typing import List


class Solution:
    def missingRolls(self, rolls: List[int], mean: int, n: int) -> List[int]:
        res = []
        sums = sum(rolls)
        m = len(rolls)
        diff = mean * (m + n) - sums
        if n <= diff <= n * 6:
            d = diff // n
            r = diff - d * n
            res = [d] * n
            for i in range(r):
                res[i] += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.missingRolls([4, 5, 6, 2, 3, 6, 5, 4, 6, 4, 5, 1, 6, 3, 1, 4, 5, 5, 3, 2, 3, 5, 3, 2, 1, 5, 4, 3, 5, 1, 5]
                         , 4
                         , 40))
