import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        n = len(target)

        @cache
        def f(mask):
            if mask == 0:
                return 0

            ans = n + 1
            for sticker in stickers:
                newMask = mask
                cnt = Counter(sticker)
                for i, c in enumerate(target):
                    if newMask & 1 << i and cnt[c]:
                        cnt[c] -= 1
                        newMask ^= 1 << i
                if newMask < mask:
                    ans = min(ans, f(newMask) + 1)
            return ans

        res = f(1 << n - 1)
        return res if res <= n else - 1

    # bfs效果优于状态压缩dp
    def minStickers2(self, stickers: List[str], target: str) -> int:

        def trans(s):
            cnts = Counter()
            for c in s:
                if c in target:
                    cnts[c] += 1
            return cnts

        availables = [c for st in stickers if (c := trans(st))]
        queue = deque([(target, 0)])
        explored = {target}
        while queue:
            cur, step = queue.popleft()
            if not cur:
                return step
            for avl in availables:
                if cur[0] in avl:
                    nxt = cur
                    for k, v in avl.items():
                        nxt = nxt.replace(k, '', v)
                    if nxt not in explored:
                        explored.add(nxt)
                        queue.append((nxt, step + 1))
        return -1


if __name__ == '__main__':
    s = Solution()

    print(s.minStickers2(stickers=["with", "example", "science"], target="thehat"))
    print("aaaaaaaaaaaaaaaabbcc".replace("a", "b", 10))
