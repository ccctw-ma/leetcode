from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional


class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        arr = [(h, n) for h, n in zip(heights, names)]
        arr.sort(key=lambda x: -x[0])
        return list(map(lambda x: x[1], arr))

    def longestSubarray(self, nums: List[int]) -> int:
        max_num = max(nums)
        n = len(nums)
        res = 1
        for i, num in enumerate(nums):
            if i > 0 and num == nums[i - 1]:
                continue
            if num == max_num:
                l = i - 1
                while l >= 0 and (nums[l] & num) == num:
                    l -= 1
                r = i + 1
                while r < n and (nums[r] & num) == num:
                    r += 1
                res = max(res, r - l - 1)
        return res

    def goodIndices(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        lg, rg = [0] * n, [0] * n

        for i, num in enumerate(nums):
            if i == 0 or num > nums[i - 1]:
                lg[i] = i
            else:
                lg[i] = lg[i - 1]
        for i in range(n - 1, -1, -1):
            if i == n - 1 or nums[i] > nums[i + 1]:
                rg[i] = i
            else:
                rg[i] = rg[i + 1]
        # print(lg, rg)
        res = []
        for i in range(k, n - k):
            l, r = i - 1, i + 1
            if l - lg[l] + 1 >= k and rg[r] - r + 1 >= k:
                res.append(i)

        return res

    def numberOfGoodPaths(self, vals: List[int], edges: List[List[int]]) -> int:
        n = len(vals)
        f = [i for i in range(n)]

        def find(x):
            while x != f[x]:
                f[x] = f[f[x]]
                x = f[x]
            return x

        def union(x, y):
            px, py = find(x), find(y)
            if px == py:
                return
            if py < px:
                px, py = py, px
            f[px] = py

        edges.sort(key=lambda x: max(vals[x[0]], vals[x[1]]))
        buc = defaultdict(list)
        for i, v in enumerate(vals):
            buc[v].append(i)
        res, i = n, 0
        while i < n - 1:
            a, b = edges[i]
            tar = max(vals[a], vals[b])
            while i < n - 1:
                u, v = edges[i]
                if max(vals[u], vals[v]) != tar:
                    break
                union(u, v)
                i += 1

            counter = defaultdict(int)
            for node in buc[tar]:
                counter[find(node)] += 1
            for v in counter.values():
                res += v * (v - 1) // 2
        return res



if __name__ == '__main__':
    s = Solution()
    # print(s.longestSubarray(
    #     [586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 516529,
    #      516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529,
    #      516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529, 516529,
    #      516529, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816,
    #      55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816,
    #      55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816,
    #      55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816, 55816,
    #      55816, 55816, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730, 586730,
    #      586730, 232392, 232392, 294503]))
    # print(s.goodIndices(nums=[2, 1, 1, 1, 3, 4, 1], k=2))
    # print(s.goodIndices(nums=[2, 1, 1, 2], k=2))
    # print(s.goodIndices([878724, 201541, 179099, 98437, 35765, 327555, 475851, 598885, 849470, 943442]
    #                     , 4))
    # print(s.numberOfGoodPaths(vals=[1, 3, 2, 1, 3], edges=[[0, 1], [0, 2], [2, 3], [2, 4]]))
    # print(s.numberOfGoodPaths(vals=[1, 1, 2, 2, 3], edges=[[0, 1], [1, 2], [2, 3], [2, 4]]))
    # print(s.numberOfGoodPaths([1], []))
    print(s.numberOfGoodPaths([2, 5, 5, 1, 5, 2, 3, 5, 1, 5],
                              [[0, 1], [2, 1], [3, 2], [3, 4], [3, 5], [5, 6], [1, 7], [8, 4], [9, 7]]))
