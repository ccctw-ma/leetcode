import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def maxValue(self, root: TreeNode, k: int) -> int:

        @cache
        def fn(root, t):
            if not root:
                return 0
            # 不染色
            res = fn(root.left, k) + fn(root.right, k)
            # 染色
            for l in range(t):
                res = max(res, fn(root.left, l) + root.val + fn(root.right, t - l - 1))
            return res

        return fn(root, k)


if __name__ == '__main__':
    s = Solution()
