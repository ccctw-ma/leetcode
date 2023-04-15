from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, pairwise
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict


class Solution:
    pass


if __name__ == '__main__':
    s = Solution()
    arr = [1, 2, 3, 4]
    print(list(pairwise(arr)))
    print(reduce(lambda x, y: x + y, arr, 20))
