from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def customSortString(self, order: str, s: str) -> str:

        return ''.join(sorted(list(s), key=lambda x: order.find(x)))


if __name__ == '__main__':
    s = Solution()
    print(s.customSortString(order="cba", s="abcd"
                             ))
