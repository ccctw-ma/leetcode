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
    def monkeyMove(self, n: int) -> int:

        mod = 10 ** 9 + 7

        @cache
        def f(x):
            if x == 0:
                return 1
            if x == 1:
                return 2
            else:
                res = f(x // 2)
                if x % 2 == 0:
                    return (res * res) % mod
                else:
                    return (((res * res) % mod) * 2) % mod

        return (f(n) - 2 + mod) % mod

    def putMarbles(self, weights: List[int], k: int) -> int:
        if k == 1:
            return 0
        n = len(weights)
        arr = []
        for i in range(n - 1):
            arr.append(weights[i] + weights[i + 1])
        arr.sort()
        k -= 1
        print(arr)
        print(sum(arr[n - k:]))
        print(sum(arr[:k]))
        return sum(arr[n - k - 1:]) - sum(arr[:k])

    def countQuadruplets(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0

        note0 = [[0] * n for _ in range(n)]
        for j in range(1, n - 2):
            cnt = sum(nums[l] > nums[j] for l in range(j + 1, n))
            for k in range(j + 1, n - 1):
                if nums[j] > nums[k]:
                    note0[j][k] = cnt
                else:
                    cnt -= 1

        note1 = [[0] * n for _ in range(n)]
        for k in range(2, n - 1):
            cnt = sum(nums[k] > nums[i] for i in range(k))
            for j in range(k - 1, 0, -1):
                if nums[j] > nums[k]:
                    note1[j][k] = cnt  # 实际上这里可以直接算 ans 了，但保留代码对称性
                else:
                    cnt -= 1

        for j in range(1, n - 2):
            for k in range(j + 1, n - 1):
                ans += note0[j][k] * note1[j][k]
        return ans


if __name__ == '__main__':
    s = Solution()

    # print(s.monkeyMove(996194169))

    # print(s.putMarbles(weights=[1, 3, 5, 1], k=2))

    print(s.countQuadruplets(nums=[1, 3, 2, 4, 5]))
