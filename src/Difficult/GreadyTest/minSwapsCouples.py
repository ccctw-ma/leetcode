from typing import List
import heapq
from collections import defaultdict, deque
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def minSwapsCouples(self, row: List[int]) -> int:
        def swap(x, y):
            row[x], row[y] = row[y], row[x]

        buc = {v: i for i, v in enumerate(row)}
        n = len(row)
        res = 0
        for i in range(0, n, 2):
            a, b = row[i], row[i + 1]
            if abs(a - b) == 1 and min(a, b) % 2 == 0:
                continue
            tar = a + 1 if a % 2 == 0 else a - 1
            tarIndex = buc[tar]
            swap(i + 1, tarIndex)
            buc[b] = tarIndex
            buc[tar] = i + 1
            res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.minSwapsCouples([0, 2, 1, 3]))
