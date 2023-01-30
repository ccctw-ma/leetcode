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
    def minCost(self, colors: str, neededTime: List[int]) -> int:
        res = 0
        n = len(colors)
        i = 0
        while i < n:
            j = i + 1
            cm = neededTime[i]
            cs = neededTime[i]
            while j < n and colors[j] == colors[j - 1]:
                cs += neededTime[j]
                cm = max(cm, neededTime[j])
                j += 1
            res += cs - cm
            i = j
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.minCost(colors="aabaa", neededTime=[1, 2, 3, 4, 1]))
