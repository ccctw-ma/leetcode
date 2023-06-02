import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby, count
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    pass


if __name__ == '__main__':
    s = Solution()
    buc = OrderedDict()
    for i in range(10, 0, - 1):
        buc[i] = 21
    print(buc)

    for a, b in pairwise([-1, 0, 1, 0, -1]):
        print(a, b)
    print(list(compress([1, 2], [4, 5, 6, 7, 8])))
    print(list(product(range(5), range(10))))
    print(list(combinations([1, 2, 3], 2)))
    print(list(permutations([1, 2, 3], 2)))

