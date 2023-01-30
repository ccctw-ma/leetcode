import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def crackSafe(self, n: int, k: int) -> str:
        # seen = set()
        # ans = list()
        # highest = 10 ** (n - 1)
        #
        # def dfs(node: int):
        #     for x in range(k):
        #         nei = node * 10 + x
        #         if nei not in seen:
        #             seen.add(nei)
        #             dfs(nei % highest)
        #             ans.append(str(x))
        #
        # dfs(0)
        # return "".join(ans) + "0" * (n - 1)
        ans = list()
        nodeNum = k ** (n - 1)
        edges = [k - 1] * nodeNum

        node = 0
        while edges[node] >= 0:
            edge = edges[node]
            edges[node] -= 1
            node = (node * k + edge) % nodeNum
            ans.append(str(edge))

        return "0" * (n - 1) + "".join(ans)


if __name__ == '__main__':
    s = Solution()
    print(s.crackSafe(2, 2))
    print("Hello world")
