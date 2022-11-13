from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def magicalString(self, n: int) -> int:
        dp = [1, 1, 1] + [0] * n
        arr = [1, 2, 2]
        flag = True
        i = 2
        j = 3
        while len(arr) <= n:
            a = 1 if flag else 2
            num = arr[i]
            for _ in range(num):
                arr.append(a)
                dp[j] = dp[j - 1] + int(a == 1)
                j += 1
            flag = not flag
            i += 1
        # print(arr)
        # print(dp)
        return dp[n - 1]


if __name__ == '__main__':
    s = Solution()
    print(s.magicalString(50))
