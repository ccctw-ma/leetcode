import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right

HIGH_BIT = 14


class TrieNode:
    def __init__(self):
        self.children = [None, None]
        self.sum = 0


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def add(self, num: int) -> None:
        cur = self.root
        for k in range(HIGH_BIT, -1, -1):
            bit = (num >> k) & 1
            if not cur.children[bit]:
                cur.children[bit] = TrieNode()
            cur = cur.children[bit]
            cur.sum += 1

    def get(self, num: int, x: int) -> int:
        res = 0
        cur = self.root
        for k in range(HIGH_BIT, -1, -1):
            bit = (num >> k) & 1
            if (x >> k) & 1:
                if cur.children[bit]:
                    res += cur.children[bit].sum
                if not cur.children[bit ^ 1]:
                    return res
                cur = cur.children[bit ^ 1]
            else:
                if not cur.children[bit]:
                    return res
                cur = cur.children[bit]
        res += cur.sum
        return res


class Solution:
    def countPairs(self, nums: List[int], low: int, high: int) -> int:
        def f(nums: List[int], x: int) -> int:
            res = 0
            trie = Trie()
            for i in range(1, len(nums)):
                trie.add(nums[i - 1])
                res += trie.get(nums[i], x)
            return res

        return f(nums, high) - f(nums, low - 1)


# 待 今天状态不是很好
if __name__ == '__main__':
    s = Solution()
    print(s.countPairs(nums=[9, 8, 4, 2, 1], low=5, high=14))
