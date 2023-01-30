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
    # 写两个版本的
    def maxSumOfThreeSubarrays(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        preSum = [0] + list(accumulate(nums))

        @cache
        def f(i, t):
            if t == 1:
                if i == k - 1:
                    return preSum[i + 1], [0]
                else:
                    s, ids = f(i - 1, t)
                    curS = preSum[i + 1] - preSum[i + 1 - k]
                    if curS > s:
                        return curS, [i + 1 - k]
                    else:
                        return s, ids
            else:
                if i == t * k - 1:
                    s, ids = f(i - k, t - 1)
                    curS = preSum[i + 1] - preSum[i + 1 - k]
                    return s + curS, ids + [i + 1 - k]
                else:
                    s, ids = f(i - 1, t)
                    curS = preSum[i + 1] - preSum[i + 1 - k]
                    ps, pids = f(i - k, t - 1)
                    if curS + ps > s:
                        return ps + curS, pids + [i + 1 - k]
                    else:
                        return s, ids

        return f(n - 1, 3)[1]

    # 三个滑动窗口 该算法更优
    def maxSumOfThreeSubarrays2(self, nums: List[int], k: int) -> List[int]:
        ans = []
        sum1, maxSum1, maxSum1Idx = 0, 0, 0
        sum2, maxSum12, maxSum12Idx = 0, 0, ()
        sum3, maxTotal = 0, 0
        for i in range(k * 2, len(nums)):
            sum1 += nums[i - k * 2]
            sum2 += nums[i - k]
            sum3 += nums[i]
            if i >= k * 3 - 1:
                if sum1 > maxSum1:
                    maxSum1 = sum1
                    maxSum1Idx = i - k * 3 + 1
                if maxSum1 + sum2 > maxSum12:
                    maxSum12 = maxSum1 + sum2
                    maxSum12Idx = (maxSum1Idx, i - k * 2 + 1)
                if maxSum12 + sum3 > maxTotal:
                    maxTotal = maxSum12 + sum3
                    ans = [*maxSum12Idx, i - k + 1]
                sum1 -= nums[i - k * 3 + 1]
                sum2 -= nums[i - k * 2 + 1]
                sum3 -= nums[i - k + 1]
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.maxSumOfThreeSubarrays(nums=[1, 2, 1, 2, 6, 7, 5, 1], k=2))
    print(s.maxSumOfThreeSubarrays(nums=[1, 2, 1, 2, 1, 2, 1, 2, 1], k=2))
