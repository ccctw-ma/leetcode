from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def avoidFlood(self, rains: List[int]) -> List[int]:
        empty = deque()
        rain = defaultdict(int)
        n = len(rains)
        ans = [1] * n
        for i in range(n):
            if rains[i] == 0:
                empty.append(i)
                continue
            if rains[i] in rain.keys():
                if len(empty) == 0:
                    return []
                date = rain.get(rains[i])
                found = False
                for d in empty:
                    if d > date:
                        ans[d] = rains[i]
                        found = True
                        empty.remove(d)
                        break
                if not found:
                    return []
            ans[i] = -1
            rain[rains[i]] = i
        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.avoidFlood([69, 0, 0, 0, 69]))
    print(s.avoidFlood([0, 1, 1]
                       ))
