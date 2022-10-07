from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def stoneGameVI(self, aliceValues: List[int], bobValues: List[int]) -> int:
        totalValues = [(a + b) for a, b in zip(aliceValues, bobValues)]
        totalValues.sort(reverse=True)
        # 所有Alice能拿到的石头的总价值，其中每个都多拿了Bob的对应石子,再减去本来就是Bob拿的石子，正好是Bob的所有石子
        ans = sum(totalValues[::2]) - sum(bobValues)
        if ans > 0:
            return 1
        elif ans < 0:
            return -1
        return 0


if __name__ == '__main__':
    s = Solution()
    print(s.stoneGameVI(aliceValues=[2, 4, 3], bobValues=[1, 6, 7]))
    print(s.stoneGameVI([9, 8, 3, 8],
                        [10, 6, 9, 5]))
