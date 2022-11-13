from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def minimumTotalDistance(self, robot: List[int], factory: List[List[int]]) -> int:
        robot.sort()
        factory.sort()
        n, m = len(robot), len(factory)
        dp = [[10 ** 12] * (n + 1) for _ in range(m + 1)]
        for i in range(m + 1):
            dp[i][0] = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                # 第i个工厂一个也不修
                dp[i][j] = min(dp[i - 1][j], dp[i][j])
                up = min(j, factory[i - 1][1])
                cost = 0
                for k in range(1, up + 1):
                    cost += abs(robot[j - k] - factory[i - 1][0])
                    dp[i][j] = min(dp[i][j], dp[i - 1][j - k] + cost)

        return dp[m][n]


if __name__ == '__main__':
    s = Solution()
    # print(s.minimumTotalDistance(robot=[0, 4, 6], factory=[[2, 2], [6, 2]]))
    print(s.minimumTotalDistance(robot=[1, -1], factory=[[-2, 1], [2, 1]]))
