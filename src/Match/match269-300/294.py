import collections
from bisect import bisect, bisect_left, bisect_right
from functools import lru_cache
from itertools import combinations, accumulate
from math import inf
from typing import Optional, List

import sortedcontainers


class Solution:
    def percentageLetter(self, s: str, letter: str) -> int:
        count = 0
        for c in s:
            if c == letter:
                count += 1

        p = (count * 100) // len(s)
        return p

    def maximumBags(self, capacity: List[int], rocks: List[int], additionalRocks: int) -> int:
        diff = []
        for c, r in zip(capacity, rocks):
            diff.append(c - r)
        diff.sort()
        count = 0
        index = 0
        while index < len(diff) and additionalRocks > 0:
            if additionalRocks >= diff[index]:
                count += 1
                additionalRocks -= diff[index]
                index += 1
            else:
                break
        return count

    def minimumLines(self, stockPrices: List[List[int]]) -> int:
        length = len(stockPrices)
        if length == 1:
            return 0
        if length == 2:
            return 1

        stockPrices.sort()
        res = 1
        a, b, c, d = stockPrices[0][0], stockPrices[0][1], stockPrices[1][0], stockPrices[1][1]
        for i in range(2, length):
            e, f = stockPrices[i]

            if (d - b) * (e - c) != (f - d) * (c - a):
                res += 1
            a, b, c, d = c, d, e, f
        return res

    def totalStrength(self, strength: List[int]) -> int:
        MOD = 10 ** 9 + 7

        n = len(strength)
        left, st = [-1] * n, []  # left[i] 为左侧严格小于 strength[i] 的最近元素位置（不存在时为 -1）
        for i, v in enumerate(strength):
            while st and strength[st[-1]] >= v: st.pop()
            if st: left[i] = st[-1]
            st.append(i)

        right, st = [n] * n, []  # right[i] 为右侧小于等于 strength[i] 的最近元素位置（不存在时为 n）
        for i in range(n - 1, -1, -1):
            while st and strength[st[-1]] > strength[i]: st.pop()
            if st: right[i] = st[-1]
            st.append(i)

        s = [0] * (n + 1)  # 前缀和
        for i, v in enumerate(strength):
            s[i + 1] = (s[i] + v) % MOD
        ss = [0] * (n + 2)  # 前缀和的前缀和
        for i, v in enumerate(s):
            ss[i + 1] = (ss[i] + v) % MOD

        ans = 0
        for i, v in enumerate(strength):
            l, r = left[i] + 1, right[i] - 1  # [l, r]  左闭右闭
            res = ((i - l + 1) * (ss[r + 2] - ss[i + 1]) - (r - i + 1) * (ss[i + 1] - ss[l])) % MOD
            ans = (ans + res * v) % MOD  # 累加贡献
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.totalStrength(strength=[1, 3, 1, 2]))
    print(s.totalStrength(strength=[5, 4, 6]))
