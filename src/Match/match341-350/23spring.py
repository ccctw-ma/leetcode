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
    def supplyWagon(self, supplies: List[int]) -> List[int]:
        n = len(supplies)
        while len(supplies) != n // 2:
            minS = 10 ** 9
            minIdx = 0
            for i in range(len(supplies) - 1):
                t = supplies[i] + supplies[i + 1]
                if t < minS:
                    minS = t
                    minIdx = i
            supplies[minIdx] = minS
            supplies.pop(minIdx + 1)
        return supplies

    def adventureCamp(self, expeditions: List[str]) -> int:
        n = len(expeditions)
        buc = set(expeditions[0].split("->"))
        maxFind, ans = 0, -1
        for i in range(1, n):
            if len(expeditions[i]) == 0:
                continue
            tmp = set(expeditions[i].split("->"))
            newS = tmp | buc
            add = len(newS) - len(buc)
            if add > maxFind:
                maxFind = add
                ans = i
            buc = newS
        return ans

    def fieldOfGreatestBlessing(self, forceField: List[List[int]]) -> int:
        arr = []
        for i, (x, y, p) in enumerate(forceField):
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
                x, y, p = forceField[j]
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


if __name__ == '__main__':
    s = Solution()
    # print(s.adventureCamp(expeditions=["", "Gryffindor->Slytherin->Gryffindor", "Hogwarts->Hufflepuff->Ravenclaw"]))
    # print(s.adventureCamp(["Alice->Dex", "", "Dex"]))
    print(s.fieldOfGreatestBlessing(forceField=[[4, 4, 6], [7, 5, 3], [1, 6, 2], [5, 6, 3]]))
    print(s.fieldOfGreatestBlessing(forceField=[[0, 0, 1], [1, 0, 1]]))
    print(s.fieldOfGreatestBlessing(
        [[565, 34, 242], [299, 628, 870], [724, 673, 221], [128, 267, 917], [561, 488, 696], [341, 71, 604],
         [835, 92, 846], [945, 696, 973], [554, 776, 430], [209, 134, 594], [987, 898, 282], [819, 154, 462],
         [618, 946, 505], [561, 40, 677], [602, 863, 657], [295, 83, 718], [456, 920, 939], [94, 717, 813],
         [611, 946, 366], [818, 850, 85], [395, 554, 333], [325, 700, 628], [590, 401, 119], [248, 858, 499],
         [298, 723, 602], [189, 538, 646], [194, 283, 344], [842, 535, 866], [192, 832, 195]]))
