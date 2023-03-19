import time, re
from operator import xor
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
    def maxScore(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        preSum = list(accumulate(nums))
        res = 0
        for c in preSum:
            if c > 0:
                res += 1
        return res

    def beautifulSubarrays(self, nums: List[int]) -> int:
        buc = defaultdict(int)
        buc[0] += 1
        cur = 0
        res = 0
        for c in nums:
            cur ^= c
            if cur in buc:
                res += buc[cur]
            buc[cur] += 1
        return res

    def findMinimumTime(self, tasks: List[List[int]]) -> int:

        tasks.sort(key=lambda x: x[1])
        # print(tasks)
        buc = [c for _, _, c in tasks]
        arr = [[] for _ in range(2001)]
        for i, (s, e, c) in enumerate(tasks):
            for j in range(s, e + 1):
                arr[j].append(i)
        res = 0
        visted = [False] * 2001
        for i, (s, e, c) in enumerate(tasks):
            if buc[i] == 0:
                continue

            for j in range(e, -1, -1):
                if visted[j]:
                    continue
                for k in arr[j]:
                    if buc[k]:
                        buc[k] -= 1
                res += 1
                visted[j] = True
                if buc[i] == 0:
                    break
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.beautifulSubarrays(nums=[4, 3, 1, 2, 4]))
    # print(s.beautifulSubarrays(nums=[1, 10, 4]))
    # print(s.beautifulSubarrays(nums=[1, 2, 4, 8, 16, 16, 8, 4, 2, 1, 3]))
    print(s.findMinimumTime(tasks=[[1, 3, 2], [2, 5, 3], [5, 6, 2]]))
    print(s.findMinimumTime(tasks=[[2, 3, 1], [4, 5, 1], [1, 5, 2]]))
    print(s.findMinimumTime([[1, 18, 5], [3, 15, 1]]))
    print(s.findMinimumTime([[10, 16, 3], [10, 20, 5], [1, 12, 4], [8, 11, 2]]))
