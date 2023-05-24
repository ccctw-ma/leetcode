import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, \
    combinations_with_replacement, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right

from Medium.TreeTest.TreeNode import TreeNode


class Solution:
    def maxSumBST(self, root: Optional[TreeNode]) -> int:
        ans = 0

        def dfs(root):
            nonlocal ans
            if not root:
                return True, 0, -inf, inf
            l = dfs(root.left)
            r = dfs(root.right)
            if l[0] and r[0]:
                if root.left and root.left.val >= root.val:
                    return False, 0, 0, 0
                if root.right and root.right.val <= root.val:
                    return False, 0, 0, 0
                if root.val >= r[2] or root.val <= l[3]:
                    return False, 0, 0, 0

                ans = max(ans, root.val + l[1] + r[1])
                return True, root.val + l[1] + r[1], min(root.val, l[2]), max(root.val, r[3])
            return False, 0, 0, 0
        dfs(root)
        return ans


if __name__ == '__main__':
    s = Solution()
