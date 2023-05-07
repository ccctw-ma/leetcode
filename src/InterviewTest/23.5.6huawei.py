import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right

from  collections import  Counter
# def func():
#     n = int(input())
#     arr = list(map(lambda x: int(x, 16), input().split(" ")))
#     bits = []
#     for num in arr:
#         for i in range(15, -1, -1):
#             if num & (1 << i):
#                 bits.append(1)
#             else:
#                 bits.append(0)
#     bits = bits[:n]
#     cnt = Counter(bits)
#     if cnt[1] == n or cnt[0] > cnt[1]:
#         print(0)
#         return
#
#     nebits = list(map(lambda x: x ^ 1, bits))
#     lspan = 0
#     for span in range(1, n):
#         flag = True
#         for i, c in enumerate(bits):
#             if c == 1:
#                 continue
#             else:
#                 if i + span > n or bits[i + span] == 0:
#                     flag = False
#                     break
#         if flag:
#             lspan = span
#             break
#
#     rspan = 0
#     for span in range(1, n):
#         flag = True
#         for i in range(len(bits) - 1, -1, -1):
#             if bits[i] == 1:
#                 continue
#             else:
#                 if i - span < 0 or bits[i - span] == 0:
#                     flag = False
#                     break
#         if flag:
#             rspan = span
#             break
#
#     # arr = []
#     # i = 0
#     # while i < n:
#     #     j = i + 1
#     #     while j < n and bits[j] == bits[i]:
#     #         j += 1
#     #     arr.append((bits[i], j - i))
#     #     i = j
#     # rightF = True
#     # rans = 0
#     # r = ''
#     # for i in range(len(arr)):
#     #     if arr[i][0] == 0:
#     #         if i > 0 and arr[i - 1][1] >= arr[i][1]:
#     #             rans = max(rans, arr[i][1])
#     #         else:
#     #             rightF = False
#     #             break
#     l, r = '', ''
#     if rspan != 0:
#         tr = nebits[rspan:] + [0] * rspan
#         r = "".join(map(str, tr))
#     # leftF = True
#     # lans = 0
#     # l = ''
#     # for i in range(len(arr) - 1, -1, -1):
#     #     if arr[i][0] == 0:
#     #         if i < len(arr) - 1 and arr[i + 1][1] >= arr[i][1]:
#     #             lans = max(lans, arr[i][1])
#     #         else:
#     #             leftF = False
#     #             break
#
#     if lspan != 0:
#         lr = [0] * lspan + nebits[:len(nebits) - lspan]
#         l = "".join(map(str, lr))
#     if lspan != 0 and rspan != 0:
#         print(2)
#         print(f'+{rspan}')
#         print(r)
#         print(f'-{lspan}')
#         print(l)
#     elif lspan != 0:
#         print(1)
#         print(f'-{lspan}')
#         print(l)
#     elif rspan != 0:
#         print(1)
#         print(f'+{rspan}')
#         print(r)
#     else:
#         print(0)
#

#

def func():
    buc = {
        ('!', '!'): 0,
        ('@', '!'): 13,
        ('!', '@'): 13,
        ('!', '#'): 4,
        ('#', '!'): 4,
        ('@', '@'): 7,
        ('#', '@'): 20,
        ('@', '#'): 20,
        ('#', '#'): 5
    }
    chars = '#@!'
    n = int(input().strip())
    s = input().strip()
    l, r = s.split("+")
    if '.' not in l:
        l += '.0'
    if '.' not in r:
        r += '.0'
    al, ar = l.split('.')
    bl, br = r.split('.')
    def fn(s1, s2, carry):
        la, lb = len(s1), len(s2)
        size = max(la, lb)
        arr1, arr2 = ['0'] * size, ['0'] * size
        for i, c in enumerate(s1):
            arr1[i] = c
        for i, c in enumerate(s2):
            arr2[i] = c
        ans = ['0'] * size
        for i in range(size - 1, -1, -1):
            if arr1[i] in chars:
                tmp = buc[(arr1[i], arr2[i])]
                mod = (tmp + carry) % 10
                carry = (tmp + carry) // 10
            else:
                a, b = int(arr1[i]), int(arr2[i])
                mod = (a + b + carry) % 10
                carry = (a + b + carry) // 10
            ans[i] = str(mod)
        return ''.join(ans), carry

    rs, rc = fn(ar, br, 0)
    ls, lc = fn(al, bl, rc)
    ans = (str(lc) if lc else '') + ls + '.' + rs
    ans = float(ans)
    if int(ans) == ans:
        print(int(ans))
    else:
        print(ans)


if __name__ == '__main__':
    func()

    # a = '0xE77F'
    # print(bin(int('14', 16)))
    # print(float('0250'))
    # print(int('0250'))
