import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class FrequencyTracker:

    def __init__(self):
        self.buc = defaultdict(int)
        self.freqs = defaultdict(int)

    def add(self, number: int) -> None:
        self.buc[number] += 1
        freq = self.buc[number]
        if freq > 1:
            self.freqs[freq - 1] -= 1
        self.freqs[freq] += 1

    def deleteOne(self, number: int) -> None:
        if number in self.buc:
            freq = self.buc[number]
            self.buc[number] -= 1
            self.freqs[freq] -= 1
            if freq == 1:
                del self.buc[number]
            else:
                self.freqs[freq - 1] += 1

    def hasFrequency(self, frequency: int) -> bool:
        return self.freqs[frequency] > 0


class Solution:
    def colorTheArray(self, n: int, queries: List[List[int]]) -> List[int]:
        cnt = 0
        arr = [0] * n
        ans = []

        def fn(idx):
            l = 0 if idx == 0 else int(arr[idx - 1] == arr[idx] and arr[idx] != 0)
            r = 0 if idx == n - 1 else int(arr[idx] == arr[idx + 1] and arr[idx] != 0)
            return l + r

        for idx, color in queries:
            pre = fn(idx)
            arr[idx] = color
            cur = fn(idx)
            cnt += (cur - pre)
            ans.append(cnt)
        return ans

    def minIncrements(self, n: int, cost: List[int]) -> int:
        def dfs(root):
            if root > n:
                return 0
            nonlocal res
            l = dfs(root * 2 + 1)
            r = dfs(root * 2 + 2)
            res += abs(l - r)
            return cost[root] + max(l, r)

        res = 0
        dfs(0)
        return res


if __name__ == '__main__':
    s = Solution()
