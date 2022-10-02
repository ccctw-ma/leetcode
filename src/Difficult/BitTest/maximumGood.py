from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional


class Solution:
    def maximumGood(self, statements: List[List[int]]) -> int:
        def check(s: int) -> int:
            cnt = 0  # i 中好人个数
            for i, row in enumerate(statements):  # 枚举 i 中的好人 j
                if (s >> i) & 1:
                    if any(st < 2 and st != (s >> j) & 1 for j, st in enumerate(row)):
                        return 0  # 好人 j 的某个陈述 st 与实际情况矛盾
                    cnt += 1
            return cnt

        return max(check(s) for s in range(1, 1 << len(statements)))

if __name__ == '__main__':
    s = Solution()
    print(s.maximumGood(statements=[[2, 1, 2], [1, 2, 2], [2, 0, 2]]))
    print('f' in "fasdfas")