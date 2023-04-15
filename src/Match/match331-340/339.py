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
    def findTheLongestBalancedSubstring(self, s: str) -> int:
        res = 0
        n = len(s)
        for i in range(n):
            if s[i] == '1':
                continue
            j = i
            while j < n and s[j] == '0':
                j += 1
            t = j
            while t < n and s[t] == '1':
                t += 1
            zero = j - i
            one = t - j
            res = max(res, min(zero, one) * 2)
        return res

    def findMatrix(self, nums: List[int]) -> List[List[int]]:
        res = []
        cnt = Counter(nums)
        n = len(nums)
        while n:
            tmp = []
            for k in cnt.keys():
                if cnt[k] > 0:
                    tmp.append(k)
                    cnt[k] -= 1
                    n -= 1
            res.append(tmp)
        return res

    def miceAndCheese(self, reward1: List[int], reward2: List[int], k: int) -> int:
        diffs = [(x - y, i) for i, (x, y) in enumerate(zip(reward1, reward2))]
        diffs.sort(key=lambda x: -x[0])
        res = 0
        n = len(diffs)
        for i in range(n):
            res += reward1[diffs[i][1]] if i < k else reward2[diffs[i][1]]
        return res

    def minReverseOperations(self, n: int, p: int, banned: List[int], k: int) -> List[int]:
        banned = set(banned)
        vis = [False] * n
        vis[p] = True
        pds = [x for x in range(k - 1, 0, -2)]
        nds = [-x for x in pds]
        # print(ds)
        res = [-1] * n
        res[p] = 0
        q = deque([p])
        step = 1
        while q:
            nn = len(q)
            for _ in range(nn):
                tmp = q.popleft()
                for d in pds:
                    pad = (k - d - 1) // 2
                    t = tmp + d
                    l, r = tmp - pad, tmp - pad + k - 1
                    if l >= 0 and r < n and t not in banned and not vis[t]:
                        vis[t] = True
                        res[t] = step
                        q.append(t)
                    if l < 0:
                        break
                for d in nds:
                    pad = (k + d - 1) // 2
                    t = tmp + d
                    l, r = tmp + pad - k + 1, tmp + pad
                    if l >= 0 and r < n and t not in banned and not vis[t]:
                        vis[t] = True
                        res[t] = step
                        q.append(t)
                    if r >= n:
                        break

            step += 1
        for i, f in enumerate(vis):
            if not f:
                res[i] = -1
        return res

    def minReverseOperations2(self, n: int, p: int, banned: List[int], k: int) -> List[int]:
        s = set(banned) | {p}
        not_banned = [[], []]
        for i in range(n):
            if i not in s:
                not_banned[i % 2].append(i)
        not_banned[0].append(n)
        not_banned[1].append(n)  # 哨兵

        fa = [list(range(len(not_banned[0]))), list(range(len(not_banned[1])))]

        def find(i: int, x: int) -> int:
            f = fa[i]
            if f[x] != x:
                f[x] = find(i, f[x])
            return f[x]

        def merge(i: int, from_: int, to: int) -> None:
            x, y = find(i, from_), find(i, to)
            fa[i][x] = y

        ans = [-1] * n
        q = [p]
        step = 0
        while q:
            tmp = q
            q = []
            for i in tmp:
                ans[i] = step
                # 从 mn 到 mx 的所有位置都可以翻转到
                mn = max(i + k - (i * 2 + 1), i - k + 1)
                mx = min(i - k + ((n - 1 - i) * 2 + 1), i + k - 1)
                a = not_banned[mn % 2]
                j = find(mn % 2, bisect_left(a, mn))
                while a[j] <= mx:
                    q.append(a[j])
                    merge(mn % 2, j, j + 1)  # 删除 j
                    j = find(mn % 2, j + 1)
            step += 1
        return ans

    def minReverseOperations3(self, n: int, p: int, banned: List[int], k: int) -> List[int]:
        # 滑窗从[i - k + 1,i]出发
        ans = [10 ** 9] * n
        for i in banned:
            ans[i] = -1
        odd = SortedList([i for i in range(n) if ans[i] != -1 and i != p and i % 2])
        even = SortedList([i for i in range(n) if ans[i] != -1 and i != p and i % 2 == 0])
        q = deque([p])
        cnt = 0
        while q:
            for _ in range(len(q)):
                T = q.popleft()
                ans[T] = cnt
                l = max(0, T - k + 1)
                r = min(T, n - k)

                if k % 2 == T % 2:
                    right = odd.bisect_right(2 * r + k - 1 - T)
                    left = odd.bisect_left(2 * l + k - 1 - T)
                    for _ in range(right - left):
                        x = odd.pop(left)
                        q.append(x)
                else:
                    right = even.bisect_right(2 * r + k - 1 - T)
                    left = even.bisect_left(2 * l + k - 1 - T)
                    for _ in range(right - left):
                        x = even.pop(left)
                        q.append(x)
            cnt += 1
        ans = [-1 if i == 10 ** 9 else i for i in ans]
        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.findTheLongestBalancedSubstring(s="01000111"
    #                                         ))
    # print(s.findTheLongestBalancedSubstring("0"))
    # print(s.findTheLongestBalancedSubstring(s="111"))
    # print(s.findMatrix([1, 3, 4, 1, 2, 3, 1]
    #                    ))
    # print(s.miceAndCheese(reward1=[1, 1, 3, 4], reward2=[4, 4, 1, 1], k=2))
    # print(s.minReverseOperations(n=4, p=2, banned=[0, 1, 3], k=1
    #                              ))
    # print(s.minReverseOperations(n=4, p=0, banned=[1, 2], k=4))
    # print(s.minReverseOperations(n=5, p=0, banned=[2, 4], k=3))
    # print(s.minReverseOperations(4
    #                              , 0
    #                              , []
    #                              , 4))
    print(s.minReverseOperations2(4
                                  , 2
                                  , []
                                  , 4))
