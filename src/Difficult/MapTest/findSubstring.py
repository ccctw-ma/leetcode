from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        tot, buc, cnt = 0, SortedDict(), Counter(words)
        for w in words:
            ll = len(w)
            tot += ll
            if ll not in buc:
                buc[ll] = set()
            buc[ll].add(w)

        if tot > len(s):
            return []

        def check(s):
            if len(s) == 0:
                return True
            for l, ws in buc.items():
                if l > len(s):
                    break
                ts = s[:l]
                if ts in ws and cnt[ts] != 0:
                    cnt[ts] -= 1
                    f = check(s[l:])
                    cnt[ts] += 1
                    if f:
                        return True
            return False

        res = []
        for i in range(0, len(s) - tot + 1):
            ts = s[i: i + tot]
            if check(ts):
                res.append(i)

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.findSubstring(s="barfoothefoobarman", words=["foo", "bar"]))
    print(s.findSubstring(s="barfoofoobarthefoobarman", words=["bar", "foo", "the"]))
