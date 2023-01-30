import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class SeatManager:

    def __init__(self, n: int):
        self.h = list(range(1, n + 1))
        heapify(self.h)

    def reserve(self) -> int:
        return heappop(self.h)

    def unreserve(self, seatNumber: int) -> None:
        heappush(self.h, seatNumber)


class Solution:
    def replaceDigits(self, s: str) -> str:
        arr = []
        i = 0
        n = len(s)
        while i < n:
            a = s[i]
            arr.append(a)
            if i + 1 < n:
                b = s[i + 1]
                c = chr(ord(a) + int(b))
                arr.append(c)
            i += 2
        return ''.join(arr)

    def maximumElementAfterDecrementingAndRearranging(self, arr: List[int]) -> int:
        n = len(arr)
        arr.sort()
        arr[0] = 1
        for i in range(1, n):
            if arr[i] - arr[i - 1] > 1:
                arr[i] = arr[i - 1] + 1
        return arr[-1]

    def closestRoom(self, rooms: List[List[int]], queries: List[List[int]]) -> List[int]:
        rooms.sort(key=lambda x: -x[1])
        ids = []
        res = [-1] * len(queries)
        n = len(rooms)
        idx = 0
        for i, query in sorted(enumerate(queries), key=lambda x: -x[1][1]):
            tid, tsize = query
            while idx < n and rooms[idx][1] >= tsize:
                insort(ids, rooms[idx][0])
                idx += 1
            if len(ids) == 0:
                res[i] = -1
            elif len(ids) == 1:
                res[i] = ids[0]
            else:
                k = bisect_left(ids, tid)
                if k == 0:
                    res[i] = ids[0]
                elif k == len(ids):
                    res[i] = ids[-1]
                else:
                    a, b = abs(tid - ids[k - 1]), abs(tid - ids[k])
                    if a <= b:
                        res[i] = ids[k - 1]
                    else:
                        res[i] = ids[k]
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.closestRoom(rooms=[[2, 2], [1, 2], [3, 2]], queries=[[3, 1], [3, 3], [5, 2]]))
    print(s.closestRoom(rooms=[[1, 4], [2, 3], [3, 5], [4, 1], [5, 2]], queries=[[2, 3], [2, 4], [2, 5]]))
    arr = [(1, 2), (1, 3), (2, 1), (2, 2), (4, 3), (4, 4)]
    insort_left(arr, (2, 3))

    print(arr)
    print(factorial())