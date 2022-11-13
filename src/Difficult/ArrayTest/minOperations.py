from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        a = sorted(set(nums))
        m, left = len(a), 0
        # 找保留最多的窗口
        for i in range(m):
            if a[i] - a[left] >= n:
                left += 1
        return n - (m - left)


if __name__ == '__main__':
    s = Solution()
    print(s.minOperations(nums=[1, 2, 3, 5, 6]))
