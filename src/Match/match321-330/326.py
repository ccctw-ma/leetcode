import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Prime:
    def prime_sieve(self, n):
        """returns a sieve of primes >= 5 and < n"""
        flag = n % 6 == 2
        sieve = bytearray((n // 3 + flag >> 3) + 1)
        for i in range(1, int(n ** 0.5) // 3 + 1):
            if not (sieve[i >> 3] >> (i & 7)) & 1:
                k = (3 * i + 1) | 1
                for j in range(k * k // 3, n // 3 + flag, 2 * k):
                    sieve[j >> 3] |= 1 << (j & 7)
                for j in range(k * (k - 2 * (i & 1) + 4) // 3, n // 3 + flag, 2 * k):
                    sieve[j >> 3] |= 1 << (j & 7)
        return sieve

    def prime_list(self, n):
        """returns a list of primes <= n"""
        res = []
        if n > 1:
            res.append(2)
        if n > 2:
            res.append(3)
        if n > 4:
            sieve = self.prime_sieve(n + 1)
            res.extend(
                3 * i + 1 | 1 for i in range(1, (n + 1) // 3 + (n % 6 == 1)) if not (sieve[i >> 3] >> (i & 7)) & 1)
        return res

    def __init__(self, n) -> None:
        self.primes = self.prime_list(n)

    def dissolve(self, num):
        '''prime factor decomposition of num'''
        lst = []
        idx = -1
        for prime in self.primes:
            if prime * prime > num:
                break

            if num % prime == 0:
                lst.append([prime, 0])
                idx += 1

            while num % prime == 0:
                lst[idx][1] += 1
                num //= prime

        if num != 1:
            lst.append([num, 1])

        return lst


class Solution:
    def distinctPrimeFactors(self, nums: List[int]) -> int:
        p = Prime(1000)
        s = set()
        for c in nums:
            for k, n in p.dissolve(c):
                s.add(k)
        return len(s)

    def minimumPartition(self, s: str, k: int) -> int:
        span = len(str(k))
        res = 0
        i = 0
        n = len(s)
        while i < n:

            if int(s[i:i + span]) <= k:
                i += span
                res += 1

            elif span == 1:
                return -1
            else:
                i += span - 1
                res += 1
        return res

    def closestPrimes(self, left: int, right: int) -> List[int]:
        p = Prime(right)
        arr = p.primes
        n = len(arr)
        MAX = 10 ** 9 + 7
        mins = MAX
        res = [-1, -1]
        for i in range(n - 1):
            if arr[i] < left:
                continue
            if arr[i +1] - arr[i] < mins:
                res = [arr[i], arr[i + 1]]
                mins = arr[i + 1] -arr[i]

        return [-1, - 1] if mins == MAX else res


if __name__ == '__main__':
    s = Solution()
    # print(s.distinctPrimeFactors(nums=[2, 4, 3, 7, 10, 6]))
    # print(s.minimumPartition(s="165462", k=60))
    # print(s.minimumPartition(s="238182", k=5))
    print(s.closestPrimes(left=10, right=19))
