from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        res = 1
        maxQ, minQ = deque(), deque()
        l = 0
        for r, x in enumerate(nums):
            while maxQ and maxQ[-1] < x:
                maxQ.pop()
            while minQ and minQ[-1] > x:
                minQ.pop()
            maxQ.append(x)
            minQ.append(x)
            while maxQ and minQ and maxQ[0] - minQ[0] > limit:
                if maxQ[0] == nums[l]:
                    maxQ.popleft()
                if minQ[0] == nums[l]:
                    minQ.popleft()
                l += 1
            res = max(res, r - l + 1)
        return res

if __name__ == '__main__':
    s = Solution()
