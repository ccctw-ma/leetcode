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
    def minimumBuckets(self, hamsters: str) -> int:
        if hamsters == 'H':
            return -1

        arr = [c for c in hamsters]
        res = 0
        n = len(arr)
        for i, c in enumerate(arr):
            if c == 'H':
                if i == 0:
                    if arr[i + 1] == 'H':
                        return -1
                    else:
                        arr[i + 1] = 'B'
                        res += 1
                elif i == n - 1:
                    if arr[i - 1] == 'H':
                        return -1
                    if arr[i - 1] == '.':
                        res += 1
                else:
                    if arr[i - 1] == 'H' and arr[i + 1] == 'H':
                        return -1
                    if arr[i + 1] == 'H' and arr[i - 1] == '.':
                        arr[i - 1] = 'B'
                        res += 1
                    if arr[i + 1] == '.' and arr[i - 1] != 'B':
                        arr[i + 1] = 'B'
                        res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.minimumBuckets("H..H"))
