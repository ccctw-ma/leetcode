from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def findGoodStrings(self, n: int, s1: str, s2: str, evil: str) -> int:
        # 灵神的数位dp板子 + KMP(第28题的模板)
        Mod = 10 ** 9 + 7

        def getNext(Next, needle):
            Next[0] = 0
            i = 1
            j = 0
            while i < len(needle):
                if needle[i] == needle[j]:
                    Next[i] = j + 1
                    i += 1
                    j += 1
                elif j > 0:
                    j = Next[j - 1]
                else:
                    i += 1
            return Next

        Next = getNext([0] * len(evil), evil)

        def get_evil_int(j, evil_int):
            while 1:
                if j == ord(evil[evil_int]):
                    return evil_int + 1
                elif evil_int > 0:
                    evil_int = Next[evil_int - 1]
                else:
                    return 0

        @cache
        def dfs(i: int, is_up_limit: bool, is_down_limit: bool, evil_int: int) -> int:
            if evil_int == len(evil):
                return 0
            if i == len(s1):
                return 1
            ans = 0
            down = 97 if not is_down_limit else ord(s1[i])
            up = 122 if not is_up_limit else ord(s2[i])
            for j in range(down, up + 1):
                ans += dfs(i + 1, is_up_limit and (j == up), is_down_limit and (j == down), get_evil_int(j, evil_int))
            return ans % Mod

        return dfs(0, True, True, 0)


if __name__ == '__main__':
    s = Solution()
