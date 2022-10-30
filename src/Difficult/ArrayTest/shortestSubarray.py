from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def shortestSubarray(self, nums: List[int], k: int) -> int:
        preSumArr = [0] + list(accumulate(nums))
        res = len(nums) + 1
        q = deque()
        for i, x in enumerate(preSumArr):
            while q and x - preSumArr[q[0]] >= k:
                res = min(res, i - q.popleft())
            while q and x <= preSumArr[q[-1]]:
                q.pop()
            q.append(i)
        return res if res < len(nums) + 1 else -1


if __name__ == '__main__':
    s = Solution()
    print(s.shortestSubarray(nums=[2, -1, 2], k=3))
