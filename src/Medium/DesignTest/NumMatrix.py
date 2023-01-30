import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class NumMatrix:

    def __init__(self, matrix: List[List[int]]):
        self.ROW = len(matrix)
        self.COL = len(matrix[0])
        self.mat = [[0] * (self.COL + 1) for _ in range(self.ROW + 1)]

        for i in range(self.ROW):
            for j in range(self.COL):
                self.mat[i + 1][j + 1] = self.mat[i][j + 1] + self.mat[i + 1][j] - self.mat[i][j] + matrix[i][j]

    def sumRegion(self, row1: int, col1: int, row2: int, col2: int) -> int:
        return self.mat[row2 + 1][col2 + 1] - self.mat[row2 + 1][col1] - self.mat[row1][col2 + 1] + self.mat[row1][col1]


# Your NumMatrix object will be instantiated and called as such:
# obj = NumMatrix(matrix)
# param_1 = obj.sumRegion(row1,col1,row2,col2)


if __name__ == '__main__':
    s = NumMatrix()
