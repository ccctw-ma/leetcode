import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def sortTheStudents(self, score: List[List[int]], k: int) -> List[List[int]]:
        return sorted(score, key=lambda x: -x[k])

    def makeStringsEqual(self, s: str, target: str) -> bool:
        if s == target:
            return True
        cnt = Counter(s)
        oz, zo = 0, 0
        for i, a in enumerate(s):
            if a == target[i]:
                continue
            else:
                b = target[i]
                if a == '0' and b == '1':
                    zo += 1
                else:
                    oz += 1
        if zo != 0 and cnt['1'] == 0:
            return False
        cnt['1'] += zo
        cnt['0'] -= zo
        return cnt['1'] - oz >= 1

    def minCost(self, nums: List[int], k: int) -> int:

        n = len(nums)

        dp = [[0] * n for _ in range(n)]
        for i in range(n):
            arr = [0] * n
            pre = 0
            for j in range(i, - 1, - 1):
                c = nums[j]
                arr[c] += 1
                if arr[c] == 1:
                    dp[j][i] = pre
                elif arr[c] == 2:
                    dp[j][i] = pre + 2
                else:
                    dp[j][i] = pre + 1
                pre = dp[j][i]
        print(dp)

        @cache
        def f(i, j):
            if i > j:
                return 0
            if i == j:
                return k
            if dp[i][j] <= k:
                return dp[i][j] + k
            res = inf
            for t in range(i, j + 1):
                res = min(res, dp[i][t] + k + f(t + 1, j))
            return res

        return f(0, n - 1)


if __name__ == '__main__':
    s = Solution()
    # print(s.sortTheStudents(score=[[10, 6, 9, 1], [7, 5, 11, 2], [4, 8, 3, 15]], k=2))
    # print(s.makeStringsEqual("1111", "0001"))
    # print(s.minCost(nums=[1, 2, 1, 2, 1, 3, 3], k=2))
    # print(s.minCost(nums=[1, 2, 1, 2, 1], k=2))
    # print(s.minCost(nums=[1, 2, 1, 2, 1], k=5))
    print(s.minCost([3, 3, 3, 3, 4, 5, 4, 6, 2, 4, 2, 1, 5, 6, 4, 5, 1, 1, 3, 3]
                    , 1))
