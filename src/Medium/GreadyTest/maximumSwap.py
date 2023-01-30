from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def maximumSwap(self, num: int) -> int:
        arr = list(map(int, str(num)))
        # temp = [(v, i) for i, v in enumerate(arr)]
        # temp.Template(key=lambda x: (-x[0], x[1]))
        # print(temp)
        # for i in range(len(arr)):
        #     if arr[i] != temp[i][0]:
        #         j = i + 1
        #         while j < len(arr) and temp[i][0] == temp[j][0]:
        #             j += 1
        #         arr[i], arr[j - 1] = arr[j - 1], arr[i]
        #         break
        # return int(''.join(map(str, arr)))
        res = num
        n = len(arr)
        for i in range(n - 1):
            for j in range(i + 1, n):
                arr[i], arr[j] = arr[j], arr[i]
                res = max(res, int(''.join(map(str, arr))))
                arr[i], arr[j] = arr[j], arr[i]
        return res

if __name__ == '__main__':
    s = Solution()
    print(s.maximumSwap(2736))
    print(s.maximumSwap(9973))
    print(s.maximumSwap(1993))
    print(s.maximumSwap(98368))
