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

from src.Medium.TreeTest import TreeNode


class Solution:
    def btreeGameWinningMove(self, root: Optional[TreeNode], n: int, x: int) -> bool:
        pm = defaultdict(None)
        buc = defaultdict(None)
        cnt = defaultdict(int)

        def dfs(root: Optional[TreeNode]):
            if not root:
                return 0
            buc[root.val] = root
            if root.left:
                pm[root.left.val] = root
            if root.right:
                pm[root.right.val] = root
            left = dfs(root.left)
            right = dfs(root.right)
            count = 1 + left + right
            cnt[root.val] = count
            return count

        dfs(root)
        xx = x
        x = buc[x]
        if x.left:
            left = cnt[x.left.val]
            if left > n - left:
                return True
        if x.right:
            right = cnt[x.right.val]
            if right > n - right:
                return True
        if pm[xx]:
            c = cnt[xx]
            if n - c > c:
                return True
        return False


if __name__ == '__main__':
    s = Solution()
