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
    def evenOddBit(self, n: int) -> List[int]:
        a, b = 0, 0
        i = 0
        while n:
            if n & 1:
                if i % 2 == 0:
                    a += 1
                else:
                    b += 1
            n >>= 1
            i += 1
        return [a, b]

    def checkValidGrid(self, grid: List[List[int]]) -> bool:
        if grid[0][0] != 0:
            return False
        buc = defaultdict(tuple)
        n = len(grid)
        for i in range(n):
            for j in range(n):
                buc[grid[i][j]] = (i, j)
        x, y = buc[0]

        def check(a, b, c, d):
            dx = abs(a - c)
            dy = abs(b - d)
            return dx + dy == 3

        for i in range(1, n * n):
            nx, ny = buc[i]
            if not check(x, y, nx, ny):
                return False
            x, y = nx, ny
        return True

    def beautifulSubsets(self, nums: List[int], k: int) -> int:
        # nums.sort()
        # res = 0
        # n = len(nums)
        # dp = [0] * n
        # for i in range(n):
        #     dp[i] = 1
        #     for j in range(i - 1, -1, -1):
        #         if nums[i] - nums[j] != k:
        #             dp[i] += dp[j]
        #         else:
        #             dp[i] -= dp[j]
        # print(dp)
        # return sum(dp)

        nums.sort()
        res = 0
        n = len(nums)
        buc = {x: i for i, x in enumerate(nums)}
        arr = []
        for i in range(n):
            if nums[i] + k in buc:
                arr.append((1 << i) + (1 << buc[nums[i] + k]))

        def check(s):
            for m in arr:
                t = m & s
                if t & (t - 1):
                    return False
            return True

        for mask in range(1, 1 << n):
            if check(mask):
                res += 1
        return res

    def findSmallestInteger(self, nums: List[int], value: int) -> int:
        cnt = Counter([x % value for x in nums])
        i = 0
        while True:
            if cnt[i % value] == 0:
                return i
            cnt[i % value] -= 1
            i += 1


if __name__ == '__main__':
    s = Solution()
    # print(s.evenOddBit(2))
    # print(s.checkValidGrid(
    #     grid=[[0, 11, 16, 5, 20], [17, 4, 19, 10, 15], [12, 1, 8, 21, 6], [3, 18, 23, 14, 9], [24, 13, 2, 7, 22]]))
    # print(s.checkValidGrid(
    #     [[24, 11, 22, 17, 4],
    #      [21, 16, 5, 12, 9],
    #      [6, 23, 10, 3, 18],
    #      [15, 20, 1, 8, 13],
    #      [0, 7, 14, 19, 2]]))
    print(s.beautifulSubsets(nums=[2, 4, 6], k=2
                             ))
    print(s.beautifulSubsets([10, 4, 5, 7, 2, 1]
                             , 3))
    print(s.beautifulSubsets([16, 1, 18, 12, 11, 5, 17, 15, 14, 8, 20, 2, 4, 6, 19, 7, 13, 10, 9, 3], 1))
    # print(s.findSmallestInteger(nums=[1, -10, 7, 13, 6, 8], value=5))
    # print(s.findSmallestInteger([3, 0, 3, 2, 4, 2, 1, 1, 0, 4]
    #                             , 5))
