import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def removeInvalidParentheses2(self, s: str) -> List[str]:
        n = len(s)
        res = []

        def check(x):
            la = 0
            for c in x:
                if c == '(':
                    la += 1
                elif c == ')':
                    la -= 1
                    if la < 0:
                        return False
            return la == 0

        def tb(i, pre):
            nonlocal res
            if i == n:
                if check(pre):
                    if not res or len(pre) > len(res[0]):
                        res = [pre]
                    elif res and len(pre) == len(res[0]):
                        res.append(pre)
                return
            if s[i].isalpha():
                tb(i + 1, pre + s[i])
                return
            tb(i + 1, pre + s[i])
            tb(i + 1, pre)

        tb(0, '')
        return list(set(res))

    def removeInvalidParentheses(self, s: str) -> List[str]:
        res = []
        lremove, rremove = 0, 0
        for c in s:
            if c == '(':
                lremove += 1
            elif c == ')':
                if lremove == 0:
                    rremove += 1
                else:
                    lremove -= 1

        def isValid(str):
            cnt = 0
            for c in str:
                if c == '(':
                    cnt += 1
                elif c == ')':
                    cnt -= 1
                    if cnt < 0:
                        return False
            return cnt == 0

        def helper(s, start, lremove, rremove):
            if lremove == 0 and rremove == 0:
                if isValid(s):
                    res.append(s)
                return
            for i in range(start, len(s)):
                if i > start and s[i] == s[i - 1]:
                    continue
                # 如果剩余的字符无法满足去掉的数量要求，直接返回
                if lremove + rremove > len(s) - i:
                    break
                # 尝试去掉一个左括号
                if lremove > 0 and s[i] == '(':
                    helper(s[:i] + s[i + 1:], i, lremove - 1, rremove)
                # 尝试去掉一个右括号
                if rremove > 0 and s[i] == ')':
                    helper(s[:i] + s[i + 1:], i, lremove, rremove - 1)
                # 统计当前字符串中已有的括号数量

        helper(s, 0, lremove, rremove)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.removeInvalidParentheses(s="()())()"))
    print(s.removeInvalidParentheses(s="(a)())()"))
