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


#
# from collections import defaultdict, deque
# def func():
#     N = int(input())
#     deg = defaultdict(int)
#     g = defaultdict(list)
#     for i in range(N):
#         arr = list(map(int, input().strip().split(" ")))
#         c = arr[0]
#         a = i + 1
#         deg[a] = c
#         for j in range(c):
#             b = arr[j + 1]
#             g[b].append(a)
#
#     q = deque()
#
#     for k, v in deg.items():
#         if v == 0:
#             q.append(k)
#
#     visited = set()
#     step = 0
#     while q:
#         step += 1
#         for _ in range(len(q)):
#             x = q.popleft()
#             visited.add(x)
#             for y in g[x]:
#                 deg[y] -= 1
#                 if deg[y] == 0:
#                     q.append(y)
#     print(-1 if len(visited) != N else step)
#
# if __name__ == '__main__':
#     func()

#
# class Node:
#     def __init__(self, key):
#         self.key = key
#         self.pre = None
#         self.next = None
#
# from collections import defaultdict
# def func():
#     l, r = list(map(int, input().split(" ")))
#     buc = defaultdict(lambda: Node(-1))
#     for id in range(l, r + 1):
#         buc[id] = Node(id)
#     head, tail = Node(-1), Node(-1)
#     for id in range(l, r + 1):
#         buc[id].pre = buc[id - 1]
#         buc[id].next = buc[id + 1]
#
#     head.next = buc[l]
#     buc[l].pre = head
#     buc[r].next = tail
#     tail.pre = buc[r]
#
#     n = int(input())
#     for _ in range(n):
#         t, id = list(map(int, input().split(" ")))
#         if t == 1:
#             if id <= len(buc):
#                 root = head
#                 for _ in range(id):
#                     key = root.next.key
#                     root.next = root.next.next
#                     del buc[key]
#         elif t == 2:
#             if l <= id <= r:
#                 if id in buc:
#                     buc[id].pre.next = buc[id].next
#                     del buc[id]
#         else:
#             if l <= id <= r:
#                 if id not in buc:
#                     tmp = Node(id)
#                     buc[id] = tmp
#                     tmp.pre = tail.pre
#                     tmp.next = tail
#
#     print(head.next.key)
def func():
    def fieldOfGreatestBlessing(arrs: List[List[int]]) -> int:
        arr = []
        for i, (x, y, p) in enumerate(arrs):
            p = p / 2
            arr.append((x - p, 1, i))
            arr.append((x + p, -1, i))
        arr.sort(key=lambda x: (x[0], -x[1]))
        vis = set()
        res = 1
        for _, f, i in arr:
            if f == 1:
                vis.add(i)
            else:
                vis.remove(i)
            tmp = []
            for j in vis:
                x, y, p = arr[j]
                p = p / 2
                tmp.append((y - p, 1))
                tmp.append((y + p, -1))
            tmp.sort(key=lambda x: (x[0], -x[1]))
            c = 0
            b = 0
            for _, f in tmp:
                b += f
                c = max(c, b)
            res = max(res, c)
        return res

    m = int(input())
    n = int(input())
    arr = []
    for _ in range(n):
        x, y = list(map(int, input().split(" ")))
        arr.append([x, y])
    l, r = 0, 10 ** 9
    while l < r:
        mid = (l + r - 1) // 2
        tmp = []
        for i in range(n):
            tmp.append((arr[i][0], arr[i][1], 2 * mid))
        res = fieldOfGreatestBlessing(tmp)
        if res >= m:
            r = mid
        else:
            l = mid + 1

    print(l)


if __name__ == '__main__':
    func()
