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
    def minRefuelStops(self, target: int, startFuel: int, stations: List[List[int]]) -> int:
        # # 判定所有特殊条件
        # if startFuel >= target:
        #     return 0
        # if len(stations) == 0 and startFuel < target:
        #     return -1
        # if len(stations) > 0 and startFuel < stations[0][0]:
        #     return -1
        # n = len(stations)
        # pre = startFuel
        # for i, (p, a) in enumerate(stations):
        #     if p > pre:
        #         return -1
        #     pre += a
        # if pre < target:
        #     return -1
        #
        # dp = [startFuel] * n
        # # 加油的次数
        # pre = startFuel
        # for t in range(n):
        #     pre += stations[t][1]
        #     tmp = [0] * n
        #     tmp[t] = pre
        #     if tmp[t] >= target:
        #         return t + 1
        #     for i in range(t + 1, n):
        #         tmp[i] = max(tmp[i - 1], dp[i - 1] + stations[i][1]) if dp[i] >= stations[i][0] else tmp[i - 1]
        #         if tmp[i] >= target:
        #             return t + 1
        #
        #     dp = tmp
        #     print(dp)

        dp = [startFuel] + [0] * len(stations)
        for i, (pos, fuel) in enumerate(stations):
            for j in range(i, -1, -1):
                if dp[j] >= pos:
                    dp[j + 1] = max(dp[j + 1], dp[j] + fuel)
        return next((i for i, v in enumerate(dp) if v >= target), -1)

    def minRefuelStops2(self, target: int, startFuel: int, stations: List[List[int]]) -> int:
        n = len(stations)
        ans, fuel, prev, h = 0, startFuel, 0, []
        for i in range(n + 1):
            curr = stations[i][0] if i < n else target
            fuel -= curr - prev
            while fuel < 0 and h:
                fuel -= heappop(h)
                ans += 1
            if fuel < 0:
                return -1
            if i < n:
                heappush(h, -stations[i][1])
                prev = curr
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.minRefuelStops(target=100, startFuel=10, stations=[[10, 60], [20, 30], [30, 30], [60, 40]]))
    print(s.minRefuelStops(target=1000, startFuel=299,
                           stations=[[13, 21], [26, 115], [100, 47], [225, 99], [299, 141], [444, 198], [608, 190],
                                     [636, 157], [647, 255], [841, 123]]))
    print(s.minRefuelStops(100,
                           50,
                           [[50, 50]]))
    print(s.minRefuelStops(1000,
                           83,
                           [[25, 27], [36, 187], [140, 186], [378, 6], [492, 202], [517, 89], [579, 234], [673, 86],
                            [808, 53], [954, 49]]))
    print(s.minRefuelStops(100,
                           50,
                           [[25, 30]]))
    print(s.minRefuelStops(1000,
                           299,
                           [[98, 5], [243, 71], [320, 137], [353, 88], [427, 153], [574, 194], [686, 134], [732, 134],
                            [818, 29], [949, 118]]))
