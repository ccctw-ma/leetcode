from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def flipLights(self, n: int, presses: int) -> int:
        seen = set()
        for i in range(2 ** 4):
            pressArr = [(i >> j) & 1 for j in range(4)]
            if sum(pressArr) % 2 == presses % 2 and sum(pressArr) <= presses:
                status = pressArr[0] ^ pressArr[1] ^ pressArr[3]
                if n >= 2:
                    status |= (pressArr[0] ^ pressArr[1]) << 1
                if n >= 3:
                    status |= (pressArr[0] ^ pressArr[2]) << 2
                if n >= 4:
                    status |= (pressArr[0] ^ pressArr[1] ^ pressArr[3]) << 3
                seen.add(status)
        return len(seen)


if __name__ == '__main__':
    s = Solution()
    print(s.flipLights(n=3, presses=1))
