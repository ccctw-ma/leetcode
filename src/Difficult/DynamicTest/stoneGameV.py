from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        n = len(stoneValue)
        partial_sum = list(accumulate([0] + stoneValue))

        def bi_search(left, right, val):
            if left == right:
                return left
            mid = (left + right) // 2

            if partial_sum[mid] == val:
                return mid
            if partial_sum[mid] > val:
                return bi_search(left, mid, val)
            if partial_sum[mid] < val:
                return bi_search(mid + 1, right, val)

        @cache
        def recur(left, right):
            if right == left:
                return 0
            ans = 0
            tmp = bi_search(left + 1, right + 1, (partial_sum[left] + partial_sum[right + 1]) / 2)
            pivot = tmp
            while pivot <= right:
                left_sum = partial_sum[pivot] - partial_sum[left]
                right_sum = partial_sum[right + 1] - partial_sum[pivot]
                if ans >= right_sum * 2:
                    break
                if left_sum > right_sum:
                    ans = max(ans, right_sum + recur(pivot, right))
                else:
                    ans = max(ans, left_sum + recur(left, pivot - 1), right_sum + recur(pivot, right))
                pivot += 1

            pivot = tmp - 1
            while pivot >= left + 1:
                left_sum = partial_sum[pivot] - partial_sum[left]
                if ans >= left_sum * 2:
                    break
                ans = max(ans, left_sum + recur(left, pivot - 1))
                pivot -= 1
            return ans

        return recur(0, n - 1)


if __name__ == '__main__':
    s = Solution()
    # print(s.stoneGameV(stoneValue=[6, 2, 3, 4, 5, 5]))
    # print(s.stoneGameV(stoneValue=[7, 7, 7, 7, 7, 7, 7]))
    # print(s.stoneGameV(stoneValue=[4]))
    print(s.stoneGameV(
        [4, 9000, 6, 2, 5, 5, 6, 8, 3, 7, 3, 4, 5, 2, 1, 5, 1, 6, 10, 10, 3, 3, 9, 3, 8, 5, 5, 1, 6, 6, 1, 3, 7, 3, 7,
         8, 1, 9, 5, 2, 3, 9, 2, 1, 4, 10, 2, 10, 4, 5, 6, 1, 8, 5, 10, 10, 9, 1, 2, 5, 1, 1, 10, 2, 6, 8, 3, 5, 8, 3,
         9, 3, 4, 8, 1, 6, 8, 5, 3, 7, 3, 5, 1, 10, 10, 4, 6, 6, 8, 5, 7, 4, 1, 5, 10, 2, 6, 7, 5, 7, 8, 4, 9, 5, 2, 9,
         3, 7, 9, 10, 1, 1, 4, 3, 5, 8, 9, 2, 6, 3, 9, 8, 9, 4, 4, 9, 10, 3, 7, 5, 3, 4, 2, 9, 7, 2, 3, 1, 1, 4, 10, 5,
         1, 2, 3, 2, 7, 7, 1, 5, 6, 2, 4, 9, 6, 10, 9, 7, 8, 9, 3, 3, 7, 7, 3, 2, 10, 9, 3, 4, 6, 10, 10, 2, 8, 6, 10,
         10, 6, 1, 10, 5, 1, 9, 3, 4, 3, 7, 5, 6, 9, 2, 6, 2, 4, 9, 1, 9, 1, 4, 3, 10, 3, 6, 10, 6, 10, 6, 3, 7, 7, 2,
         5, 6, 9, 10, 7, 6, 7, 3, 3, 5, 2, 9, 5, 4, 10, 6, 1, 9, 3, 6, 3, 10, 2, 6, 3, 4, 1, 10, 1, 4, 9, 5, 10, 2, 2,
         4, 8, 3, 3, 8, 10, 2, 6, 3, 8, 9, 6, 6, 7, 3, 7, 3, 2, 1, 3, 4, 3, 9, 10, 7, 4, 6, 7, 8, 3, 3, 5, 9, 8, 2, 10,
         4, 6, 7, 2, 10, 10, 2, 5, 1, 7, 2, 9, 9, 5, 1, 10, 5, 1, 1, 1, 7, 8, 10, 3, 1, 6, 3, 7, 9, 1, 10, 5, 5, 2, 5,
         10, 8, 10, 6, 6, 8, 3, 6, 4, 3, 6, 7, 8, 1, 3, 2, 1, 4, 7, 7, 8, 1, 1, 4, 3, 3, 7, 7, 7, 6, 8, 8, 1, 10, 6, 4,
         4, 9, 9, 9, 2, 3, 9, 2, 10, 2, 2, 6, 9, 9, 1, 7, 8, 1, 2, 7, 8, 8, 10, 10, 4, 10, 8, 4, 1, 6, 4, 3, 8, 6, 1, 7,
         3, 2, 7, 4, 3, 6, 4, 3, 10, 6, 10, 10, 7, 5, 10, 1, 6, 8, 6, 6, 3, 7, 8, 7, 5, 6, 5, 3, 1, 4, 4, 8, 8, 10, 7,
         4, 10, 10, 8, 5, 9, 6, 2, 7, 10, 5, 7, 1, 3, 5, 3, 5, 7, 5, 2, 10, 3, 10, 4, 6, 5, 6, 2, 2, 4, 1, 7, 1, 1, 9,
         7, 8, 7, 5, 4, 7, 4, 8, 8, 1, 2, 10, 6, 6, 1, 6, 6, 5, 8, 5, 3, 5, 9, 10, 6, 9, 4, 10, 10, 5, 4, 1, 6000,
         12500, 25000, 50000, 100000, 200000, 400000, 990000]))
