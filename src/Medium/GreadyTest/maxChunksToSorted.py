from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        res = 0
        i = 0
        temp = 0
        n = len(arr)
        while i < n:
            j = i
            cur_max = arr[j]
            cur_min = arr[j]
            while j < n:
                cur_max = max(cur_max, arr[j])
                cur_min = min(cur_min, arr[j])
                if cur_min == temp and cur_max == j:
                    break
                j += 1
            res += 1
            i = j + 1
            temp = i
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxChunksToSorted([4, 3, 2, 1, 0]))
    print(s.maxChunksToSorted([2, 0, 1]))
