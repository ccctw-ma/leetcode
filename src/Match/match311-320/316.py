from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def haveConflict(self, event1: List[str], event2: List[str]) -> bool:
        aas, aae = event1[0].split(':'), event1[1].split(':')
        ash, asm = int(aas[0]), int(aas[1])
        aeh, aem = int(aae[0]), int(aae[1])

        bs, be = event2[0].split(':'), event2[1].split(':')
        bsh, bsm = int(bs[0]), int(bs[1])
        beh, bem = int(be[0]), int(be[1])
        arr = [[(ash, asm), (aeh, aem)], [(bsh, bsm), (beh, bem)]]

        if event1[0] >= event2[0]:
            event1, event2 = event2, event1
        return event1[1] >= event2[0]

    def subarrayGCD(self, nums: List[int], k: int) -> int:
        n = len(nums)
        # res = sum(int(x == k) for x in nums)
        res = 0
        i = 0
        while i < n:
            while i < n and nums[i] % k != 0:
                i += 1
            j = i
            if j == n:
                break
            while j < n and nums[j] % k == 0:
                pre = nums[j]
                for t in range(j, i - 1, -1):
                    pre = gcd(pre, nums[t])
                    if pre == k:
                        res += (t - i + 1)
                        break

                j += 1

            i = j
        return res

    def minCost(self, nums: List[int], cost: List[int]) -> int:

        tmp = list(zip(nums, cost))
        tmp.sort()
        tot = sum(cost)
        note = 0
        for x, y in tmp:
            note += y
            if note >= tot // 2:
                chosen = x
                break
        ans = 0
        for x, y in tmp:
            ans += y * abs(x - chosen)
        return ans

    def minCost2(self, nums: List[int], cost: List[int]) -> int:
        a = sorted(zip(nums, cost))
        ans = total = sum((x - a[0][0]) * c for x, c in a)
        sum_cost = sum(cost)
        for (x0, c), (x1, _) in pairwise(a):
            sum_cost -= c * 2
            total -= sum_cost * (x1 - x0)
            ans = min(ans, total)
        return ans

    # 求中位数
    def minCost3(self, nums: List[int], cost: List[int]) -> int:
        a = sorted(zip(nums, cost))
        s, mid = 0, sum(cost) // 2
        for x, c in a:
            s += c
            if s >= mid:
                return sum(abs(y - x) * c for y, c in a)  # 把所有数变成 x

    def makeSimilar(self, nums: List[int], target: List[int]) -> int:
        ae, ao = sorted(filter(lambda x: x % 2 == 0, nums)), sorted((filter(lambda x: x % 2, nums)))
        be, bo = sorted(filter(lambda x: x % 2 == 0, target)), sorted((filter(lambda x: x % 2, target)))
        ce = sum(abs(a - b) for a, b in zip(ae, be))
        co = sum(abs(a - b) for a, b in zip(ao, bo))
        return (ce + co) // 4


if __name__ == '__main__':
    s = Solution()
    # print(s.minCost2(nums=[1, 3, 5, 2], cost=[2, 3, 1, 14]))
    # print(list(filter(lambda x: x % 2, [1, 2, 3, 4, 5])))
    # print(s.makeSimilar(nums=[1, 2, 5], target=[4, 1, 3]))
    nums = [1, 3, 4, 234, 23, 423, 4, 23, 4, 23, 423, 42, 34, 23, 423, 423]
    print(sorted(nums, key=lambda x: (x % 2, x)))
