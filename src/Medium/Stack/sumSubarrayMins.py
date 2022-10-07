from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict


class Solution:
    def sumSubarrayMins(self, arr: List[int]) -> int:
        mod = 10 ** 9 + 7
        n = len(arr)
        stk, r_min = [], [n] * n
        for i in range(n):
            while stk and arr[stk[-1]] > arr[i]:
                r_min[stk[-1]] = i
                stk.pop()
            stk.append(i)
        print(r_min)
        stk, l_min = [], [-1] * n
        for i in range(n - 1, -1, -1):
            while stk and arr[stk[-1]] >= arr[i]:
                l_min[stk[-1]] = i
                stk.pop()
            stk.append(i)
        print(l_min)
        res = 0
        for i in range(n):
            a, b = l_min[i], r_min[i]
            res = (res + (i - a) * (b - i) * arr[i]) % mod
        return res

    def sumSubarrayMins2(self, A: List[int]) -> int:
        A.append(-1)
        stack, res = [-1], 0
        for i in range(len(A)):
            while A[i] < A[stack[-1]]:
                idx = stack.pop()
                res += A[idx] * (i - idx) * (idx - stack[-1])
            stack.append(i)
        return res % (10 ** 9 + 7)


if __name__ == '__main__':
    s = Solution()
    # print(s.sumSubarrayMins(arr=[3, 1, 2, 4]))
    # print(s.sumSubarrayMins(arr=[11, 81, 94, 43, 3]))
    print(s.sumSubarrayMins2(
        [9, 12, 31, 23, 12]))
