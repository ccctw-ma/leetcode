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
    def beautifulSubsets(self, nums: List[int], k: int) -> int:
        buc = Counter(nums)
        res = 1
        for t in buc:
            if t - k not in buc:
                arr = []
                while t in buc:
                    arr.append(t)
                    t += k
                n = len(arr)
                dp = [1] * (n + 2)
                for i in range(n):
                    dp[i + 2] = dp[i + 1] + dp[i] * (pow(2, buc[arr[i]]) - 1)
                res *= dp[-1]
        return res - 1


if __name__ == '__main__':
    s = Solution()
    print(s.beautifulSubsets(nums=[2, 4, 6], k=2))
