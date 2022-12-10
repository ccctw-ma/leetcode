import bisect
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right

from src.Medium.TreeTest.TreeNode import TreeNode


class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        s = set()
        for a, b, c in combinations(nums, 3):
            # print(a, b, c)
            if a != b and b != c and a != c:
                s.add((a, b, c))
        return len(s)

    def closestNodes(self, root: Optional[TreeNode], queries: List[int]) -> List[List[int]]:

        arr = []

        def inorder(root: Optional[TreeNode]):
            if root:
                inorder(root.left)
                arr.append(root.val)
                inorder(root.right)

        inorder(root)
        print(arr)
        res = []
        for q in queries:
            index = bisect_left(arr, q)
            if index == len(arr):
                res.append([arr[index - 1], - 1])

            elif index == 0 and q != arr[index]:
                res.append([-1, arr[0]])
            elif arr[index] == q:
                res.append([q, q])
            else:
                res.append([arr[index - 1], arr[index]])
        return res

    def minimumFuelCost(self, roads: List[List[int]], seats: int) -> int:
        graph = defaultdict(list)
        for a, b in roads:
            graph[a].append(b)
            graph[b].append(a)

        vis = set()
        res = 0

        def dfs(root):
            nonlocal res
            vis.add(root)
            nei = graph[root]
            count = 0
            for x in nei:
                if x not in vis:
                    t = dfs(x)
                    res += ceil(t / seats)
                    count += t
                    # print(x, t)
            return count + 1

        dfs(0)
        return res

    def beautifulPartitions(self, s: str, k: int, minLength: int) -> int:
        p = 10 ** 9 + 7
        primes = set('2357')
        n = len(s)
        dp = [[0 for j in range(n + 1)] for i in range(k + 1)]
        dp[0][-1] = 1
        for i in range(1, k + 1):
            total = 0
            for j in range(n):
                if j + 1 >= minLength and s[j + 1 - minLength] in primes:
                    # print(s[:j - minLength], s[j-minLength + 1:])
                    total += dp[i - 1][j - minLength]
                    total %= p
                if s[j] in primes:
                    continue
                dp[i][j] = total
            print(dp[i])
        return dp[k][n - 1]

    def beautifulPartitions2(self, s: str, k: int, minLength: int) -> int:
        mod = 10 ** 9 + 7
        primes = {'2', '3', '5', '7'}
        if s[0] not in primes or s[-1] in primes: return 0
        splits = []
        n = len(s)
        for i in range(minLength - 1, n - minLength):
            if s[i] not in primes and s[i + 1] in primes:
                splits.append(i)

        @cache
        def getRes(idx, used):
            if used == k - 1: return 1
            if idx >= len(splits): return 0
            if used + len(splits) - idx < k - 1: return 0
            return (getRes(idx + 1, used) + getRes(bisect_left(splits, splits[idx] + minLength), used + 1)) % mod

        ans = getRes(0, 0)
        getRes.cache_clear()
        return ans

    def beautifulPartitions3(self, s: str, k: int, l: int) -> int:
        MOD = 10 ** 9 + 7

        def is_prime(c: str) -> bool:
            return c in "2357"

        # 判断是否可以在 j-1 和 j 之间分割（开头和末尾也算）
        def can_partition(j: int) -> bool:
            return j == 0 or j == n or not is_prime(s[j - 1]) and is_prime(s[j])

        n = len(s)
        if k * l > n or not is_prime(s[0]) or is_prime(s[-1]):  # 剪枝
            return 0
        f = [[0] * (n + 1) for _ in range(k + 1)]
        f[0][0] = 1
        for i in range(1, k + 1):
            sum = 0
            # 优化：枚举的起点和终点需要给前后的子串预留出足够的长度
            for j in range(i * l, n - (k - i) * l + 1):
                if can_partition(j - l):
                    sum = (sum + f[i - 1][j - l]) % MOD  # j'=j-l 双指针
                if can_partition(j):
                    f[i][j] = sum
        return f[k][n]


if __name__ == '__main__':
    s = Solution()
    # print(s.unequalTriplets(nums=[1,2,1]))
    # arr = [1, 3, 5, 7, 9]
    # print(bisect_left(arr, 4))
    # print(s.minimumFuelCost(roads=[[3, 1], [3, 2], [1, 0], [0, 4], [0, 5], [4, 6]], seats=2))
    # print(s.minimumFuelCost(roads=[], seats=1))
    #
    print(s.beautifulPartitions(s="23542185131", k=3, minLength=2))
    print(s.beautifulPartitions(s="23542185131", k=3, minLength=3))
    # print(s.beautifulPartitions(s="3312958", k=3, minLength=1))
    # print(s.beautifulPartitions("783938233588472343879134266968"
    #                             , 4
    #                             , 6))
    # print(s.beautifulPartitions(
    #     "7827122123628626332224743253237642274197321323876885378891734398472258226127489631471674627822745381264342416126433298372676656334657685841842774475326338826422224827374979923921897972381366936223458889871548922228768368445453788"
    #     , 30
    #     , 2))
    # print(s.beautifulPartitions(
    #     "3413219557867448395723967439296236483331263448498434272281283728412254612328347743189526678786313341487652858176235338796529925561677539417926284352164577334521626491296724279263119241756261337553642326221695113562834675241299938245626359344797836838683436613626897354987935995677137522536391126543352617787234282282994936466256283471513215999841216125496113285864979623767281148814293776514462382813515763331463325294366814875613985334511794193147427437889913894581814758789797864388185113395731871387749263958758543154432696481313427422636849883234115858259498997739624671292383312567714534212532331898746763617434961517484249399252712631978"
    #     , 98
    #     , 4))

    arr = [1, 2, 3, 4, 5, 6, 7]

    print(bisect_left(arr, 5, key=lambda x: x + 1))
