import datetime
import time
from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def bestCoordinate(self, towers: List[List[int]], radius: int) -> List[int]:
        x_max = max(t[0] for t in towers)
        y_max = max(t[1] for t in towers)
        cx = cy = max_quality = 0
        for x in range(x_max + 1):
            for y in range(y_max + 1):
                quality = 0
                for tx, ty, q in towers:
                    d = (x - tx) * (x - tx) + (y - ty) * (y - ty)
                    if d <= radius * radius:
                        quality += int(q / (1 + d ** 0.5))
                if quality > max_quality:
                    cx, cy, max_quality = x, y, quality
        return [cx, cy]


if __name__ == '__main__':
    s = Solution()
    # print(s.bestCoordinate(towers=[[1, 2, 5], [2, 1, 7], [3, 1, 9]], radius=2))
    # print(s.bestCoordinate([[2, 1, 9], [0, 1, 9]]
    #                        , 2))
    # print(s.bestCoordinate([[42, 0, 0]]
    #                        , 7))
    # print(s.bestCoordinate([[46, 48, 17], [12, 38, 14]]
    #                        , 37))
    # print(s.bestCoordinate([[0, 1, 2], [2, 1, 2], [1, 0, 2], [1, 2, 2]]
    #                        , 1))
    t1 = time.time()
    for i in range(1000000):
        j = i ** 20
        j -= 1
    print(time.time() - t1)

    t2 = time.time()
    for i in range(1000000):
        j = i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i
        j -= 1
    print(time.time() - t2)
