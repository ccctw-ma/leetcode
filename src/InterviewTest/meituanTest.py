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


def mex():
    """
    mex
    时间限制: 2000/1000 MS (Java/Others)
    内存限制: 65536/65536 K (Java/Others)
    问题描述
    若S表示一个非负整数集合，mex(S)的值为不属于集合S的最小非负整数。例如，mex({0, 1, 4}) = 2，mex({1, 2}) = 0。
    有n个互不相同的非负整数a1, a2, … an 构成了一个非负整数集合A。小美想知道若将ai (1 ≤ i ≤ n)从集合A中删除，剩下的n-1个数构成的新集合A’的mex值为多少？请输出i从1到n 所有取值下新集合的mex值。

    输入描述
    第一行输入一个整数n，表示集合A的大小。
    第二行输入n个整数a1, a2, … an 。
    输出描述
    输出n个整数，相邻两个数之间用空格隔开。其中第i个数表示从集合A中删除ai，剩下n-1 个数构成的新集合的mex值 。

    输入样例1
    4
    5 0 3 1
    输出样例1
    2 0 2 1

    数据范围和说明
    对于80%的数据, 2 ≤ n ≤ 100, 0 ≤ ai ≤ 100
    对于100%的数据，2 ≤ n ≤ 50000, 0 ≤ ai ≤ 109，保证ai互不相同。

    样例说明
    删除第1个数5，mex({0, 1, 3}) = 2；
    删除第2个数0，mex({5, 3, 1}) = 0；
    删除第3个数3，mex({5, 0, 1}) = 2；
    删除第4个数1，mex({5, 0, 3}) = 1。
    """
    n = int(input())
    arr = list(map(int, input().split(" ")))
    # print(n, arr)
    curMin = 10 ** 9
    for i, c in enumerate(sorted(arr)):
        if i != c:
            curMin = i
            break
    res = []
    for c in arr:
        if c < curMin:
            res.append(str(c))
        else:
            res.append(str(curMin))
    print(" ".join(res))


def tailer():
    n, m = list(map(int, input().split(" ")))
    S = input()
    arr = list(map(int, input().split(" ")))
    subs = [input() for s in range(m)]

    # print(n, m, S, arr, subs)
    res = set()
    vis = [False] * m

    def tb(s, start, pre):
        if start == n:
            res.add(pre)
            return
        for i in range(start, len(s)):
            tmp = s[start:i + 1]
            for j in range(m):
                if not vis[j] and tmp == subs[j]:
                    vis[j] = True
                    tb(s, i + 1, pre + " " + str(i))
                    vis[j] = False

    tb(S, 0, "")
    print(len(res))


def changeArr():
    n = int(input())
    arr = list(map(int, input().split((" "))))
    arr.sort()
    print(n, arr)

    arr = [x - i for i, x in enumerate(arr)]
    s = sum(arr)
    print(arr, s)
    res = s
    for c in arr:
        t = sum(abs(c - x) for x in arr)
        res = min(res, t)

    print(res)


# 5
# 4 0 -3 -2 -3
# 2 -2 -4 5 4

def flip():
    n = int(input())
    positives = list(map(int, input().split(" ")))
    negatives = list(map(int, input().split(" ")))
    # print(positives, negatives)
    curS = sum(positives)
    if curS == 0:
        print(0)
        return

    diffs = [a - b for a, b in zip(positives, negatives)]
    tar = curS
    # print(diffs, tar)
    dp = {0: 0}
    for i in range(n):
        tmp = {}
        t = diffs[i]
        for k, v in dp.items():
            tmp[k + t] = min(dp[k] + 1, dp[k + t] if (k + t) in dp else inf)
        dp |= tmp
    print(dp[tar] if tar in dp else -1)


if __name__ == '__main__':
    # mex()
    # tailer()
    # changeArr()
    flip()
    # a = defaultdict(lambda: inf)
    # b = defaultdict(lambda: inf)
    # a[0] = 1
    # print(a | b)
