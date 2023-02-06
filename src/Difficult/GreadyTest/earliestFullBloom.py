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


class Solution:
    def earliestFullBloom(self, plantTime: List[int], growTime: List[int]) -> int:
        # sp = sum(plantTime)
        # growTime.sort(reverse=True)
        # sm = max(x + y for x, y in zip(plantTime, growTime))
        # return max(sm + 1, sp + growTime[-1] + 1)

        arr = sorted([(x, y) for x, y in zip(plantTime, growTime)], key=lambda x: -x[1])
        t = 0
        res = 0
        for p, g in arr:
            t += p
            res = max(t + g, res)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.earliestFullBloom([27, 5, 24, 17, 27, 4, 23, 16, 6, 26, 13, 17, 21, 3, 9, 10, 28, 26, 4, 10, 28, 2],
                              [26, 9, 14, 17, 6, 14, 23, 24, 11, 6, 27, 14, 13, 1, 15, 5, 12, 15, 23, 27, 28, 12]))
