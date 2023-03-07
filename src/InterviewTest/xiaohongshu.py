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
    def test01(self, n: int, arr: List[int]) -> str:
        s, c = 0, n
        dp = [[0] * (n + 1) for _ in range(n)]
        dp[0][1] = arr[0]
        for i in range(1, n):
            for j in range(1, n + 1):
                if j > (i // 2 + 1):
                    continue
                else:
                    dp[i][j] = max(dp[i - 1][j], (dp[i - 2][j - 1] if i - 2 >= 0 else 0) + arr[i])
                    if dp[i][j] > s:
                        s = dp[i][j]
                    elif dp[i][j] == s:
                        c = min(c, j)
        return f'{s} {c}'


def check_valid(num, turn, magic, hps, damage):
    # 使用技能造成伤害但不补刀，最后剩下法力值的时候在进行补刀
    i = 0
    for i in range(num):
        # 释放技能的次数为整除的次数或者是魔力值的次数，取小的那个

        spell_time = min(hps[i] // damage, magic)
        hps[i] -= spell_time * damage
        turn -= spell_time
        magic -= spell_time
        if magic == 0: break
    # 去除刚好整除的值

    hps = sorted(hps)
    i = 0
    if hps[-1] == 0: return True
    while hps[i] == 0:
        i += 1
    hps = hps[i:]
    # 普攻或者技能能够清掉

    if sum(hps) <= turn: return True
    if len(hps) <= magic:
        return True

    # 还剩余法力值，此时怪物的血量必定都小于技能伤害，按血量从高到低使用技能

    else:
        last = len(hps) - 1
        while magic > 0:
            last -= 1
            magic -= 1
            turn -= 1
        # 无法力值，判断能否用普攻清完

        hps = hps[:last + 1]
        return turn >= sum(hps)


def main():
    num, turn, magic = list(map(int, input().split()))
    hps = list(map(int, input().split()))

    # 回合不够必定输

    if len(hps) > turn: return -1

    # 法力值为零且血量和大于回合数 必定输
    if magic == 0 and sum(hps) > turn: return -1

    left, right = 0, int(max(hps))
    while left < right:
        mid = (left + right) // 2
        # 注意python浅拷贝的坑
        if check_valid(num, turn, magic, hps.copy(), damage=mid):
            right = mid
        else:
            left = mid + 1
    # 如果left = max(hps)，同样是不存在伤害值满足条件，left一直右移直到越界

    return left if left < max(hps) else -1


print(main())
