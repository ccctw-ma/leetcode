from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def applyOperations(self, nums: List[int]) -> List[int]:
        n = len(nums)
        for i in range(n - 1):
            if nums[i] == nums[i + 1]:
                nums[i] *= 2
                nums[i + 1] = 0
            else:
                continue
        res = []
        zeros = []
        for c in nums:
            if c:
                res.append(c)
            else:
                zeros.append(c)
        return res + zeros

    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        s = defaultdict(int)
        ss = 0
        res = 0
        for i in range(k):
            s[nums[i]] += 1
            ss += nums[i]
        if len(s.keys()) == k:
            res = ss
        n = len(nums)
        for i in range(k, n):
            a = nums[i]
            b = nums[i - k]
            ss += a
            ss -= b
            s[a] += 1
            s[b] -= 1
            if s[b] == 0:
                del s[b]
            if len(s.keys()) == k:
                res = max(res, ss)
        return res

    def totalCost(self, costs: List[int], k: int, candidates: int) -> int:
        h = []

        n = len(costs)
        l, r = 0, n - 1
        for i in range(min(candidates, (n + 1) // 2)):
            heappush(h, (costs[i], i, True))
            l += 1
        for i in range(n - 1, max(n - candidates - 1, (n + 1) // 2 - 1), - 1):
            heappush(h, (costs[i], i, False))
            r -= 1
        res = 0
        for i in range(k):
            a, b, c = heappop(h)
            res += a
            if l <= r:
                if c:
                    heappush(h, (costs[l], l, True))
                    l += 1
                else:
                    heappush(h, (costs[r], r, False))
                    r -= 1
        return res

    def minimumTotalDistance(self, robot: List[int], factory: List[List[int]]) -> int:
        robot.sort()
        buc = defaultdict(int)
        fs = []
        for f, l in factory:
            buc[f] = l
            fs.append(f)
        fs.sort()
        allfac = sum(c for a, c in factory)
        n = len(fs)
        sums = [0] * n
        for i in range(n - 1, -1, -1):
            if i == n - 1:
                sums[i] = buc[fs[i]]
            else:
                sums[i] = sums[i + 1] + buc[fs[i]]
        # print(sums)
        res = 0
        for r in robot:
            index = bisect_left(fs, r)
            if index == 0 or buc[fs[index - 1]] == 0:
                res += abs(r - fs[index])
                buc[fs[index]] -= 1
            elif index == len(fs):
                res += abs(r - fs[index - 1])
                buc[fs[index - 1]] -= 1
            else:
                l, rr = index - 1, index
                da, db = abs(r - fs[l]), abs(r - fs[rr])
                remainRobot = len(robot) - r - 1
                # 只能选左边
                if remainRobot == sums[rr]:
                    res += da
                    buc[fs[l]] -= 1
                else:
                    # 选距离小的
                    if da <= db:
                        res += da
                        buc[fs[l]] -= 1
                    else:
                        res += db
                        buc[fs[rr]] -= 1
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.totalCost(costs=[17, 12, 10, 2, 7, 2, 11, 20, 8], k=3, candidates=4))
    # print(s.totalCost(costs=[1, 2, 2, 4, 1], k=3, candidates=3))
    # print(s.totalCost([31, 25, 72, 79, 74, 65, 84, 91, 18, 59, 27, 9, 81, 33, 17, 58]
    #                   , 11
    #                   , 2))
    print(bisect_left([1, 2, 4, 6], 6))
    # print(s.minimumTotalDistance(robot=[0, 4, 6], factory=[[2, 2], [6, 2]]))
    print(s.minimumTotalDistance([9, 11, 99, 101],
                                 [[10, 1], [7, 1], [14, 1], [100, 1], [96, 1], [103, 1]]))
    ss = 2 ** 514324234230000
