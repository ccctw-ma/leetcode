import os
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


def rollTheString(s, roll):
    n = len(s)
    arr = [0] * n
    for i, c in enumerate(s):
        arr[i] = ord(c) - ord('a')
    diffs = [0] * n
    diffs[0] = arr[0]
    for i in range(1, n):
        diffs[i] = arr[i] - arr[i - 1]
    for x in roll:
        diffs[0] += 1
        if x < n:
            diffs[x] -= 1
    res = ''
    base = 0
    for c in diffs:
        base += c
        res += chr(base % 26 + ord('a'))
    return res


def getPotentialOfWinner(potential, k):
    buc = Counter()
    q = deque(potential)
    while True:
        a, b = q.popleft(), q.popleft()
        winner, loser = max(a, b), min(a, b)
        buc[winner] += 1
        if buc[winner] == k:
            return winner
        q.appendleft(winner)
        q.append(loser)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    n = int(input().strip())
    ps = []
    for _ in range(n):
        ps.append(int(input().strip()))
    k = int(input().strip())
    fptr.write(f'{getPotentialOfWinner(ps, k)}\n')
    fptr.close()
