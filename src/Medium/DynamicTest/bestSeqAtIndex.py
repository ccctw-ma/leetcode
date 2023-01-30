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
    def bestSeqAtIndex(self, height: List[int], weight: List[int]) -> int:
        arr = []
        for i in range(len(height)):
            arr.append([height[i], weight[i]])
        arr.sort(key=lambda x: [x[0], -x[1]])
        arr = [x[1] for x in arr]
        f = []
        for v in arr:
            index = bisect_left(f, v)
            if index == len(f):
                f.append(v)
            else:
                f[index] = v
        return len(f)


if __name__ == '__main__':
    s = Solution()
    # print(s.bestSeqAtIndex(height=[65, 70, 56, 75, 60, 68],
    #                        weight=[100, 150, 90, 190, 95, 110]))
    # print(s.bestSeqAtIndex([1, 2, 3, 4]
    #                        , [1, 2, 3, 4]))
    # print(s.bestSeqAtIndex([1, 2, 3, 4]
    #                        , [4, 3, 2, 1]))
    print(s.bestSeqAtIndex([8378, 8535, 8998, 3766, 648, 6184, 5506, 5648, 3907, 6773],
                           [9644, 849, 3232, 3259, 5229, 314, 5593, 9600, 6695, 4340]))
