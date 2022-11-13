from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def secondGreaterElement(self, nums: List[int]) -> List[int]:
        stk, heap = [], []
        ans = [-1] * len(nums)
        for i, num in enumerate(nums):
            while heap and num > heap[0][0]:
                _, j = heappop(heap)
                ans[j] = num
            while stk and num > nums[stk[-1]]:
                j = stk.pop()
                heappush(heap, (nums[j], j))
            stk.append(i)

        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.secondGreaterElement([2, 4, 0, 9, 6]))
