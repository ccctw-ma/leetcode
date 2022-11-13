from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        res = []
        s = s[1:len(s) - 1]
        n = len(s)

        def fn(s: str) -> List[str]:
            if len(s) == 1:
                return [s]
            n = len(s)
            res = []
            if len(str(int(s))) == n:
                res.append(s)
            # add .
            for i in range(1, n):
                left, right = s[:i], s[i:]
                if left != "0" and left[:1] == "0":
                    continue
                if right[-1:] == "0":
                    continue
                res.append(f'{left}.{right}')
            return res
        for i in range(1, n):
            left = s[:i]
            right = s[i:]
            ls, rs = fn(left), fn(right)
            if len(ls) == 0 and len(rs) == 0:
                continue
            for l in ls:
                for r in rs:
                    res.append(f'({l}, {r})')
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.ambiguousCoordinates("(0123)"))
    # print(s.ambiguousCoordinates("(00011)"))
    # print(s.ambiguousCoordinates("(100)"))