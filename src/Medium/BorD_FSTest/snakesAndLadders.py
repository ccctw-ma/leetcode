import time
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right
import re


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [-1] * (n * n + 1)
        flag = True
        idx = 1
        for i in range(n - 1, -1, -1):
            if flag:
                for j in range(n):
                    if board[i][j] != -1:
                        arr[idx] = board[i][j]
                    idx += 1
            else:
                for j in range(n - 1, -1, -1):
                    if board[i][j] != -1:
                        arr[idx] = board[i][j]
                    idx += 1
            flag = not flag
        start, end = 1, n * n
        q = {start}
        vis = {start}
        step = 0
        while len(q):
            step += 1
            print(q)
            tmp = set()
            for cur in q:
                for next in range(cur + 1, min(cur + 6, end) + 1):
                    if arr[next] != -1:
                        next = arr[next]
                    if next == end:
                        return step
                    if next in vis:
                        continue
                    tmp.add(next)
                    vis.add(next)
                    # if arr[next] == -1:
                    #     tmp.add(next)
                    # elif arr[next] == end:
                    #     return step
                    # elif arr[next] != cur and arr[next] > step:
                    #     tmp.add(arr[next])
            q = tmp
        return -1


if __name__ == '__main__':
    s = Solution()
    print(s.snakesAndLadders(
        board=[[-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, 35, -1, -1, 13, -1],
               [-1, -1, -1, -1, -1, -1], [-1, 15, -1, -1, -1, -1]]))
    print(s.snakesAndLadders(board=[[-1, -1], [-1, 3]]))
    print(s.snakesAndLadders([[-1, -1, -1], [-1, 2, -1], [-1, -1, -1]]))
    print(s.snakesAndLadders([[-1, -1, -1], [-1, 9, 8], [-1, 8, 9]]))
    print(s.snakesAndLadders([[1, 1, -1], [1, 1, 1], [-1, 1, 1]]))

    print(s.snakesAndLadders([[-1, 1, 2, -1], [2, 13, 15, -1], [-1, 10, -1, -1], [-1, 6, 2, 8]]))
    print(s.snakesAndLadders([[-1, 1, 1, 1], [-1, 7, 1, 1], [1, 1, 1, 1], [-1, 1, 9, 1]]))
