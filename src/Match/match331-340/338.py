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
    def primeSubOperation(self, nums: List[int]) -> bool:
        ps = Prime(max(nums) + 1)
        primes = ps.primes
        n = len(nums)
        for i in range(n - 2, - 1, - 1):
            if nums[i] < nums[i + 1]:
                continue
            for j in primes:
                if j < nums[i] and nums[i] - j < nums[i + 1]:
                    nums[i] = nums[i] - j
                    break
            if nums[i] >= nums[i + 1]:
                return False
        return True

    def minOperations(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        preSums = [0] + list(accumulate(nums))
        n = len(nums)
        res = []
        for q in queries:
            idx = bisect_right(nums, q)
            left = q * idx - preSums[idx]
            right = preSums[-1] - preSums[idx] - q * (n - idx)
            res.append(left + right)
        return res

    def collectTheCoins(self, coins: List[int], edges: List[List[int]]) -> int:

        n = len(coins)
        g = [[] for _ in range(n)]
        deg = [0] * n
        for x, y in edges:
            g[x].append(y)
            g[y].append(x)  # 建图
            deg[x] += 1
            deg[y] += 1

        # 用拓扑排序「剪枝」：去掉没有金币的子树
        q = deque()
        for i, (d, c) in enumerate(zip(deg, coins)):
            if d == 1 and c == 0:  # 无金币叶子
                q.append(i)
        while q:
            for y in g[q.popleft()]:
                deg[y] -= 1
                if deg[y] == 1 and coins[y] == 0:
                    q.append(y)

        # 再次拓扑排序
        for i, (d, c) in enumerate(zip(deg, coins)):
            if d == 1 and c == 1:  # 有金币叶子
                q.append(i)
        if len(q) <= 1:  # 至多一个有金币的叶子，直接收集
            return 0
        time = [0] * n
        while q:
            x = q.popleft()
            for y in g[x]:
                deg[y] -= 1
                if deg[y] == 1:
                    time[y] = time[x] + 1  # 记录入队时间
                    q.append(y)

        # 统计答案
        return sum(time[x] >= 2 and time[y] >= 2 for x, y in edges) * 2


if __name__ == '__main__':
    s = Solution()
    # print(s.primeSubOperation([4, 9, 6, 10]))
    # print(s.primeSubOperation([5, 4, 3, 2, 1]))
    # print(s.minOperations(nums=[3, 1, 6, 8], queries=[1, 5, 20]))
    # print(s.minOperations(nums=[2, 9, 6, 3], queries=[10, 1, 9]))
    print(s.collectTheCoins(coins=[1, 0, 0, 0, 0, 1], edges=[[0, 1], [1, 2], [2, 3], [3, 4], [4, 5]]))
