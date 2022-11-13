from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def parseBoolExpr(self, expression: str) -> bool:
        if expression == 't':
            return True
        if expression == 'f':
            return False
        c = expression[0]
        l, r = expression.find('('), expression.rfind(')')
        inner = expression[l + 1: r]
        res = []
        cur, count = '', 0
        for i, cc in enumerate(inner):
            cur += cc
            if cc == '(':
                count += 1
            elif cc == ')':
                count -= 1
            elif cc == ',' and count == 0:
                res.append(self.parseBoolExpr(cur[:-1]))
                cur = ''

        res.append(self.parseBoolExpr(cur))
        if c == '!':
            return not res[0]
        if c == '&':
            return reduce(lambda x, y: x and y, res)
        return reduce(lambda x, y: x or y, res)

    def parseBoolExpr2(self, expression: str) -> bool:
        def function(s):
            s = s.replace("t", "True")
            s = s.replace("f", "False")
            s = s.replace("&", "andfun")
            s = s.replace("|", "orfun")
            s = s.replace("!", "notfun")
            return s

        def andfun(*args):
            result = True
            for i in args:
                result = result and i
            return result

        def orfun(*args):
            result = False
            for i in args:
                result = result or i
            return result

        def notfun(a):
            return not a

        z = function(expression)
        return eval(z)

    def parseBoolExpr3(self, expression: str) -> bool:
        stk = []
        for c in expression:
            if c == ',':
                continue
            if c != ')':
                stk.append(c)
                continue
            t = f = 0
            while stk[-1] != '(':
                if stk.pop() == 't':
                    t += 1
                else:
                    f += 1
            stk.pop()
            op = stk.pop()
            if op == '!':
                stk.append('t' if f == 1 else 'f')
            elif op == '&':
                stk.append('t' if f == 0 else 'f')
            elif op == '|':
                stk.append('t' if t else 'f')
        return stk[-1] == 't'


if __name__ == '__main__':
    s = Solution()
    # ss = '!(f)'
    # print(ss.rfind(')'))
    # print(ss.find('('))
    # print(True and False)
    print(s.parseBoolExpr(expression="|(&(t,f,t),!(t))"
                          ))
    print(s.parseBoolExpr3(expression="|(&(t,f,t),!(t))"
                           ))
