from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        n = len(stoneValue)
        dp = [0] * (n + 3)
        stoneValue += [0] * 3
        for i in range(n - 1, -1, -1):
            dp[i] = max(
                stoneValue[i] - dp[i + 1],
                stoneValue[i] + stoneValue[i + 1] - dp[i + 2],
                stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]
            )
        return "Alice" if dp[0] > 0 else "Tie" if dp[0] == 0 else "Bob"

    def stoneGameIII2(self, stoneValue: List[int]) -> str:
        n = len(stoneValue)
        stoneValue.append(0)
        f1, f2, f3 = stoneValue[n - 1], 0, 0

        for i in range(n - 2, -1, -1):
            a, b, c = stoneValue[i], stoneValue[i + 1], stoneValue[i + 2]
            one = a - f1
            two = a + b - f2
            three = a + b + c - f3
            ans = one if one > two else two
            ans = ans if ans > three else three
            f1, f2, f3 = ans, f1, f2

        if f1 > 0:
            return 'Alice'
        elif f1 < 0:
            return 'Bob'
        return 'Tie'


if __name__ == '__main__':
    s = Solution()
