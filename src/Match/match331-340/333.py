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
    def minOperations(self, n: int) -> int:
        # print(bin(n))
        s = bin(n)[2:]
        res = s.count('1')
        count = 0
        while n:
            i = 0
            while (n & (1 << i)) == 0:
                i += 1
            j = i
            while n & (1 << j):
                j += 1
            span = j - i
            if span == 1:
                n -= (1 << i)
            else:
                n += (1 << i)
            count += 1

        return min(count, res)

    ## 困难题
    def squareFreeSubsets_wrong(self, nums: List[int]) -> int:
        pris = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        mod = 10 ** 9 + 7
        arr = []
        for c in nums:
            if any([c % d == 0 for d in [4, 9, 16, 25]]):
                continue
            arr.append(c)
        cnt = Counter(arr)
        keys = list(cnt.keys())
        n = len(keys)
        keys.sort()

        def check(arr):
            for a, b in combinations(arr, 2):
                if gcd(a, b) != 1:
                    return False
            return True

        res = 0
        for i in range(n):
            res += cnt[keys[i]]
            preKeys = keys[:i]
            cur = keys[i]
            print("----" + str(keys[i]))
            for span in range(1, len(preKeys) + 1):
                for items in combinations(preKeys, span):
                    tks = list(items) + [cur]
                    if check(tks):
                        print(tks)
                        t = 1
                        for k in tks:
                            t *= cnt[k]
                        res = (res + t) % mod
        return res

    def squareFreeSubsets(self, nums: List[int]) -> int:
        PRIMES = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        MASKS = [0] * 31
        for i in range(1, 31):
            for j, p in enumerate(PRIMES):
                if i % p == 0:
                    if i % (p * p) == 0:
                        MASKS[i] = -1
                        break
                    MASKS[i] |= (1 << j)
        MOD = 10 ** 9 + 7
        M = 1 << len(PRIMES)
        f = [0] * M
        f[0] = 1
        for x in nums:
            mask = MASKS[x]
            if mask >= 0:
                for j in range(M - 1, mask - 1, -1):
                    if (mask | j) == j:
                        f[j] = (f[j] + f[mask ^ j]) % MOD
        return (sum(f) - 1) % MOD

    def findTheString(self, lcp: List[List[int]]) -> str:
        pass


if __name__ == '__main__':
    s = Solution()
    # print(s.minOperations(39))
    # print(s.minOperations(54))
    # print(s.minOperations(128))
    # print(s.minOperations(63))
    # print(s.minOperations(21))
    # print(s.squareFreeSubsets(nums=[3, 4, 4, 5]))
    # print(s.squareFreeSubsets(nums=[3, 5, 6, 10, 11, 22]))
    print(s.squareFreeSubsets(
        [1, 2, 6, 15, 7, 19, 6, 29, 28, 24, 21, 25, 25, 18, 9, 6, 20, 21, 8, 24, 14, 19, 24, 28, 30, 27, 13, 21, 1, 23,
         13, 29, 24, 29, 18, 7]))

    a = 328
    print(bin(a))
    while a:
        a = (a - 1) & a
        print(bin(a))
