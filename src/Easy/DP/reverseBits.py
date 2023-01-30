import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def reverseBits(self, num: int) -> int:
        s = bin(num)[2:]
        n = len(s)
        cnt = int(s[-1] == '1')
        rn = [0] * n
        res = 0
        for i in range(n - 2, -1, -1):
            rn[i] = cnt
            if s[i] == '1':
                cnt += 1
            else:
                cnt = 0
            res = max(res, cnt)
        print(rn)
        if res == 32:
            return 32
        res += 1
        cnt = 0
        # res = 0
        for i in range(n):
            if s[i] == '0':
                res = max(res, cnt + 1 + rn[i])
                cnt = 0
            else:
                cnt += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.reverseBits(-1))
