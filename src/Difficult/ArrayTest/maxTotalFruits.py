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
    def maxTotalFruits(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        preSum = [0]
        n = len(fruits)
        for i in range(n):
            preSum.append(preSum[-1] + fruits[i][1])
        ps = [p for p, _ in fruits]

        # 第一个 < startPos的位置
        leftBase = bisect_right(ps, startPos) - 1
        # 第一个 >= startPos 的位置
        rightBase = bisect_left(ps, startPos)

        ans = 0
        # 先往左边走, 然后如果条件允许再调头往右边走
        for i in range(leftBase, -1, -1):
            left = ps[i]
            if (startPos - left) > k:
                break
            # 此时调头可以到达的右边最大值
            right = left + k - (startPos - left)
            right = max(right, startPos)
            rdx = bisect_right(ps, right)
            ans = max(ans, preSum[rdx] - preSum[i])
        # 先往右边走， 然后如何条件允许再调头往左边走
        for i in range(rightBase, n):
            right = ps[i]
            if right - startPos > k:
                break
            # 此时调头往左走可以到达的左边最小值
            left = right - (k - (right - startPos))
            left = min(left, startPos)
            ldx = bisect_left(ps, left)
            ans = max(ans, preSum[i + 1] - preSum[ldx])
        return ans

    # 滑动窗口的方法
    def maxTotalFruits2(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        ans = 0
        n = len(fruits)
        i = 0
        j = -1
        while i < n and fruits[i][0] <= startPos:
            if startPos - fruits[i][0] <= k:
                if j == -1:
                    j = i
                ans += fruits[i][1]
            i += 1
        res = ans
        while i < n and fruits[i][0] - startPos <= k:
            while fruits[i][0] - fruits[j][0] + min(startPos - fruits[j][0], fruits[i][0] - startPos) > k:
                res -= fruits[j][1]
                j += 1
            res += fruits[i][1]
            ans = max(ans, res)
            i += 1
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.maxTotalFruits(fruits=[[2, 8], [6, 3], [8, 6]], startPos=5, k=4))
    print(s.maxTotalFruits(fruits=[[0, 9], [4, 1], [5, 7], [6, 2], [7, 4], [10, 9]], startPos=5, k=4))

    print(s.maxTotalFruits(
        [[0, 7], [7, 4], [9, 10], [12, 6], [14, 8], [16, 5], [17, 8], [19, 4], [20, 1], [21, 3], [24, 3], [25, 3],
         [26, 1], [28, 10], [30, 9], [31, 6], [32, 1], [37, 5], [40, 9]]
        , 21
        , 30))
    # arr = [2, 6, 8]
    # print(bisect_left(arr, 5))
    # print(bisect_right(arr, 9))
