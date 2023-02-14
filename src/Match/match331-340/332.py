import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def findTheArrayConcVal(self, nums: List[int]) -> int:
        res = 0
        n = len(nums)
        i, j = 0, n - 1
        while i <= j:
            if i == j:
                res += nums[i]
                return res
            a = nums[i]
            b = nums[j]
            res += int(str(a) + str(b))
            i += 1
            j -= 1
        return res

    def countFairPairs(self, nums: List[int], lower: int, upper: int) -> int:

        nums.sort()
        ans = 0
        for i, c in enumerate(nums):
            left = lower - c
            right = upper - c
            lc = bisect_left(nums, left, lo=i + 1)
            rc = bisect_right(nums, right, lo=i + 1) - 1
            ans += (rc - lc + 1)
        return ans

    def substringXorQueries(self, s: str, queries: List[List[int]]) -> List[List[int]]:
        """
        写复杂了，
        因为字符串的长度最大也就32
        直接枚举所有可能的字符串放到字典里
        后面o(n)的时间就可以完成任务了
        """
        n = len(s)
        arr = list(map(int, s))
        ones = []
        zero = inf
        for i, c in enumerate(s):
            if c == '1':
                ones.append([i, i])
            else:
                zero = min(zero, i)

        @cache
        def f(x):
            if x == 1:
                return ones
            preArr = f(x >> 1)
            tar = x & 1
            res = []
            for l, r in preArr:
                if r + 1 < n and arr[r + 1] == tar:
                    res.append([l, r + 1])
            return res

        qs = sorted([(a ^ b, i) for i, (a, b) in enumerate(queries)])
        res = [[0, 0]] * len(qs)
        for q, i in qs:
            if q == 0:
                res[i] = [-1, -1] if zero == inf else [zero, zero]
            else:
                ans = f(q)
                if ans:
                    res[i] = ans[0]
                else:
                    res[i] = [-1, -1]

        return res

    def minimumScore(self, s: str, t: str) -> int:
        n, m = len(s), len(t)
        suf = [m] * (n + 1)
        j = m - 1
        for i in range(n - 1, -1, -1):
            if j >= 0 and s[i] == t[j]:
                j -= 1
            suf[i] = j + 1
        ans = suf[0]  # 全部匹配右边的情况
        j = 0
        for i, c in enumerate(s):
            if j < m and c == t[j]:
                j += 1
                if suf[i + 1] >= j:
                    ans = min(ans, suf[i + 1] - j)
            if j == m:
                break
        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.findTheArrayConcVal(nums=[7, 52, 2, 4]))
    # print(s.countFairPairs(nums=[0, 1, 7, 4, 4, 5], lower=3, upper=6))
    # print(s.countFairPairs(nums=[1, 7, 9, 2, 5], lower=11, upper=11))
    # print(s.substringXorQueries(s="101101", queries=[[0, 5], [1, 2]]))
    # print(s.substringXorQueries(s="1", queries=[[4, 5]]))
    # print(s.substringXorQueries(s="0101", queries=[[12, 8]]))
    print(s.minimumScore(s="abacaba", t="bzaa"))
    print(s.minimumScore(s="cde", t="xyz"))
