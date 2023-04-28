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

from src.Difficult.UnionFind.largestIsland import TreeNode

# n = int(input())
# res = [0] * ((n * (n + 1)) // 2)
# idx, span = 0, 1
# while span <= n:
#     for x in range(n - span + 1, n + 1):
#         res[idx] = x
#         idx += 1
#     span += 1
# print(res)


# def valueOfTree(root: TreeNode) -> TreeNode:
#     # write code here
#     def dfs(root: TreeNode):
#         if not root:
#             return 0, 0
#         lt, lf = dfs(root.left)
#         rt, rf = dfs(root.right)
#         t, f = 0, 0
#         tmp = root.val
#         while tmp % 2 == 0:
#             t += 1
#             tmp //= 2
#         tmp = root.val
#         while tmp % 5 == 0:
#             f += 1
#             tmp //= 5
#         t += (lt + rt)
#         f += (lf + rf)
#         root.val = min(t, f)
#         return t, f
#
#     dfs(root)
#     return root
#
# node1 = TreeNode(2)
# node2 = TreeNode(5)
# node3 = TreeNode(10)
# node4 = TreeNode(4)
# node5 = TreeNode(5)
# node1.left = node2
# node1.right = node3
# node3.left = node4
# node3.right = node5
# valueOfTree(node1)
# pass


n = int(input())
ca, sa = 1, 0
cb, sb = 1, 0
cc, sc = 1, 0
mod = 10 ** 9 + 7
for i in range(n - 1):
    tca, tsa = (cb + cc) % mod, (cb + 2 * cc) % mod
    tcb, tsb = (ca + cc) % mod, (ca + cc) % mod
    tcc, tsc = (ca + cb) % mod, (ca * 2 + cb) % mod
    ca, sa = tca, tsa
    cb, sb = tcb, tsb
    cc, sc = tcc, tsc

print((sa + sb + sc) % mod)


def matrix_power(matrix, power):
    result = [[int(i == j) for j in range(len(matrix))] for i in range(len(matrix))]
    while power > 0:
        if power % 2 == 1:
            result = matrix_multiply(result, matrix)
        matrix = matrix_multiply(matrix, matrix)
        power //= 2
    return result


def matrix_multiply(matrix1, matrix2):
    result = [[0 for _ in range(len(matrix2[0]))] for _ in range(len(matrix1))]
    for i in range(len(matrix1)):
        for j in range(len(matrix2[0])):
            for k in range(len(matrix2)):
                result[i][j] = (result[i][j] + matrix1[i][k] * matrix2[k][j]) % mod
    return result


n = 4
mod = 10 ** 9 + 7
matrix = [
    [0, 1, 1, 0],
    [1, 0, 1, 0],
    [1, 1, 0, 0],
    [3, 2, 3, 1]
]
base = [[0, 0, 0, 1]]
power = n - 1
result = matrix_multiply(base, matrix_power(matrix, power))
print(result)
print(sum(result[0][:3]) % mod)
