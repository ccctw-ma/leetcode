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

from src.Medium.TreeTest.TreeNode import TreeNode


class Solution:
    def fun(self, root: TreeNode) -> TreeNode:
        # write code here
        odd = []
        even = []

        def dfs(root, level):
            if root:
                if level % 2:
                    odd.append(root)
                else:
                    even.append(root)
                dfs(root.left, level + 1)
                dfs(root.right, level + 1)
        dfs(root, 1)
        a, b = len(odd), len(even)
        n = a + b
        tot = (n * (n + 1)) // 2
        if a > b:
            a, b = b, a
            odd, even = even, odd
        # 左边少， 右边多
        left, right = tot // 2, tot // 2 + (tot % 2)
        if sum(range(n, n - a, - 1)) < left:
            return None
        arr = list(range(n, 0, -1))

        # 从arr里挑a个数构成left

        def check(c, s):
            return c * (c + 1) // 2 <= s

        target = left
        cnt = a
        cur = 0
        vis = [False] * n
        for c in arr:
            if check(cnt - 1, target - c):
                vis[c - 1] = True
                target -= c
                cnt -= 1
            if target == 0:
                break
        idx1 = 0
        idx2 = 0
        for i, f in enumerate(vis):
            if f:
                odd[idx1].val = (i + 1)
                idx1 += 1
            else:
                even[idx2].val = (i + 1)
                idx2 += 1
        return root

    def getMaxValue(self, str: str, k: int) -> int:

        n = len(str)

        @cache
        def f(i, t):
            if t == 1:
                cnt = Counter(str[i:])
                return len(cnt.keys()) * (n - i)
            cnt = Counter()
            res = inf
            for idx in range(i, n - t + 1):
                cnt[str[idx]] += 1
                tmp = len(cnt.keys()) * (idx - i + 1)
                other = f(idx + 1, t - 1)
                res = min(res, max(tmp, other))
            return res

        return f(0, k)


if __name__ == '__main__':
    s = Solution()
    print(s.getMaxValue("aacbbbb", 4))
    # root = TreeNode(-1)
    # root.left = TreeNode(-1)
    # root.right = TreeNode(-1)
    # print(s.fun(root))