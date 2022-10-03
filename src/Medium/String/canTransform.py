from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional
from functools import lru_cache


# from functools import cache
class Solution:
    def canTransform(self, start: str, end: str) -> bool:
        return start.replace('X', '') == end.replace('X', '')


if __name__ == '__main__':
    s = Solution()
    print(s.canTransform("LXXLXRLXXL",
                         "XLLXRXLXLX"))
