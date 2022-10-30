from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def beautifulBouquet(self, flowers: List[int], cnt: int) -> int:
        mod = 10 ** 9 + 7
        buc = [0] * (10 ** 5 + 5)
        n = len(flowers)
        j = 0
        res = 0
        for i in range(n):
            key = flowers[i]
            buc[key] += 1
            if buc[key] <= cnt:
                res += (i - j + 1)
            else:
                while j < i:
                    buc[flowers[j]] -= 1
                    j += 1
                    if flowers[j - 1] == key:
                        break
                res += (i - j + 1)
        return res % mod

    def Leetcode(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        n = len(words)
        dp = [None] * n
        q = deque()
        inq = [False] * (1 << 8)
        for i in range(n):
            w = words[i]
            m = len(w)
            dp[i] = [-1] * (1 << m)
            mm = (1 << m)
            dp[i][mm - 1] = 0
            for k in range(mm):
                inq[k] = False
            q.append(mm - 1)
            inq[mm - 1] = True
            while q:
                mm = q.pop()
                inq[mm] = False
                cd = dp[i][mm]
                c = 0
                for k in range(m):
                    if (1 << k) & mm: c += 1
                c1 = 0
                for k in range(m):
                    if ((1 << k) & mm) == 0: continue
                    nd = cd + c1 * (c - c1 - 1)
                    nm = mm ^ (1 << k)
                    if dp[i][nm] == -1 or dp[i][nm] > nd:
                        dp[i][nm] = nd
                        if not inq[nm]:
                            inq[nm] = True
                            q.append(nm)
                    c1 += 1
            # print i, dp[i]
        dp2 = {}
        INF = 0x7fffffff
        x = [ord(c) - ord('a') for c in "helloleetcode"]
        ws = [[ord(c) - ord('a') for c in w] for w in words]
        xc = len(x)
        cx = [0] * 26
        ncx = [0] * 26

        def check(w, wm, xm):
            c = len(w)
            for k in range(26): ncx[k] = cx[k] = 0
            for k in range(xc):
                if (1 << k) & xm:
                    cx[x[k]] += 1
            for k in range(c):
                if (1 << k) & wm:
                    ncx[w[k]] += 1
                    if ncx[w[k]] > cx[w[k]]: return 0
            rm = 0
            for k in range(xc):
                if (1 << k) & xm:
                    if ncx[x[k]]:
                        ncx[x[k]] -= 1
                        rm |= (1 << k)
            return rm

        def dfs(m, i, m2, j):
            if i >= n:
                if m == 0: return 0
                return INF
            kk = (m, i, m2)
            if kk in dp2: return dp2[kk]
            w = ws[i]
            c = len(w)
            mm = 1 << c
            r = dfs(m, i + 1, 0, 0) + dp[i][(mm - 1) ^ m2]
            ms = [0] * 26
            for k in range(xc):
                if (1 << k) & m:
                    ms[x[k]] = 1 << k
            while j < c:
                if ms[w[j]]:
                    t = dfs(m ^ ms[w[j]], i, m2 | (1 << j), j + 1)
                    r = min(r, t)
                j += 1
            """
            for cm in range(mm):
                nm = check(w, cm, m)
                if nm==0: continue
                t = dfs(m^nm, i+1)+dp[i][(mm-1)^cm]
                r = min(r, t)
            """
            dp2[kk] = r
            # print kk, r
            return r

        v = dfs((1 << xc) - 1, 0, 0, 0)
        if v >= INF:
            v = -1
        return v


if __name__ == '__main__':
    s = Solution()
    print(s.Leetcode(words=["hold", "engineer", "cost", "level"]))
