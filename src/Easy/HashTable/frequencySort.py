from functools import reduce, lru_cache, cache
from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def frequencySort(self, nums: List[int]) -> List[int]:
        buc = Counter(nums)
        arr = []
        for k, v in buc.items():
            for _ in range(v):
                arr.append([k, v])
        arr.sort(key=lambda x: (x[1], -x[0]))
        return list(map(lambda x: x[0], arr))


if __name__ == '__main__':
    s = Solution()
    print(s.frequencySort(nums=[1, 1, 2, 2, 2, 3]))
