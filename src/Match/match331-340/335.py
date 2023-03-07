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

from src.Difficult.UnionFind.largestIsland import TreeNode


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
    def kthLargestLevelSum(self, root: Optional[TreeNode], k: int) -> int:
        res = []
        q = deque([root])
        while q:
            n = len(q)
            t = 0
            for _ in range(n):
                node = q.popleft()
                t += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            res.append(t)
        res.sort(reverse=True)
        if k > len(res):
            return -1
        else:
            return res[k - 1]

    def findValidSplit(self, nums: List[int]) -> int:
        p = Prime(max(nums) + 1000)

        @cache
        def f(x):
            return p.dissolve(x)

        right = Counter()
        for num in nums:
            for a, c in f(num):
                right[a] += c
        buc = defaultdict(int)
        for i, num in enumerate(nums):
            if i == len(nums) - 1:
                continue
            for a, c in f(num):
                buc[a] += c
                right[a] -= c
            if all(right[x] == 0 for x in buc):
                return i
        return -1

    def waysToReachTarget(self, target: int, types: List[List[int]]) -> int:
        mod = 10 ** 9 + 7

        types.sort(key=lambda x: x[1])

        dp = [1] + [0] * target
        for count, mark in types:
            if mark > target:
                break
            maxC = min(count, target // mark)
            temp = dp.copy()
            for i in range(maxC):
                t = mark * (i + 1)
                for j in range(target + 1):
                    if dp[j] != 0 and j + t <= target:
                        temp[j + t] = (temp[j + t] + dp[j]) % mod

            dp = temp
        return dp[target]


if __name__ == '__main__':
    s = Solution()
    # print(gcd(4 * 7, 2 * 3 * 5))
    # print(s.findValidSplit(nums=[4, 7, 8, 15, 3, 5]))
    # print(s.findValidSplit(nums=[4, 7, 15, 8, 3, 5]))
    print(s.findValidSplit([1, 1]))
    # print(s.waysToReachTarget(target=6, types=[[6, 1], [3, 2], [2, 3]]))
    # print(s.waysToReachTarget(target=5, types=[[50, 1], [50, 2], [50, 5]]))
    # print(bin(50 >> 5))
    # print(int('1010', base=2))
