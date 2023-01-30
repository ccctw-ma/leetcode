import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def allPossibleFBT(self, n: int) -> List[Optional[TreeNode]]:
        @cache
        def build(n: int) -> List[Optional[TreeNode]]:
            if n == 1:
                return [TreeNode(0)]
            if n % 2 == 0:
                return []
            nn = n - 1
            res = []
            for k in range(1, nn):
                l, r = k, nn - k
                for left in build(l):
                    for right in build(r):
                        root = TreeNode(0)
                        root.left = left
                        root.right = right
                        res.append(root)
            return res

        return build(n)


if __name__ == '__main__':
    s = Solution()
    print(s.allPossibleFBT(5))
