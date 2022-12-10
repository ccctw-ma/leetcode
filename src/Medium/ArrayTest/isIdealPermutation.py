from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def isIdealPermutation(self, nums: List[int]) -> bool:
        local_reverse, global_resverse = 0, 0
        n = len(nums)
        l = SortedList([nums[-1]])
        for i in range(n - 2, -1, -1):
            if nums[i] > nums[i + 1]:
                local_reverse += 1
            if nums[i] < l[0]:
                global_resverse += 0
            else:
                index = bisect_left(l, nums[i])
                global_resverse += index
            l.add(nums[i])
        return local_reverse == global_resverse


if __name__ == '__main__':
    s = Solution()
    arr = [1, 3, 4]
    # print(bisect_left(arr, 5))
    print(s.isIdealPermutation([1, 2, 0]))
