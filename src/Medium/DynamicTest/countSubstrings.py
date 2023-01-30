import time, re
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class Trie:

    def __init__(self):
        self.children = [None] * 26
        self.isEnd = False
        self.count = 0

    def insert(self, s: str) -> None:
        node = self
        for ch in s:
            index = ord(ch) - ord('a')
            if not node.children[index]:
                node.children[index] = Trie()

            node = node.children[index]
        node.isEnd = True
        node.count += 1

    def searchPrefix(self, prefix: str) -> Optional["Trie"]:
        node = self
        for ch in prefix:
            index = ord(ch) - ord('a')
            if not node.children[index]:
                return None
            node = node.children[index]
        return node


class Solution:
    def countSubstrings2(self, s: str, t: str) -> int:
        buc = defaultdict(Counter)
        m, n = len(s), len(t)
        for i in range(n):
            for j in range(i, -1, -1):
                ts = t[j: i + 1]
                buc[len(ts)][ts] += 1

        @cache
        def find(ss):
            cnt = buc[len(ss)]
            res = 0
            for k, v in cnt.items():
                if compare(ss, k):
                    res += v
            return res

        @cache
        def compare(a, b):
            cnt = 0
            for x, y in zip(a, b):
                if x != y:
                    cnt += 1
                if cnt > 1:
                    break
            return cnt == 1

        res = 0
        for i in range(m):
            for j in range(i, -1, -1):
                ts = s[j:  i + 1]
                res += find(ts)

        return res

    def countSubstrings(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        res = 0
        for d in range(-m + 1, n):
            i, j = 0, 0
            if d < 0:
                i = -d
            else:
                j = d
            fij, gij = 0, 0
            while i < m and j < n:
                if s[i] == t[j]:
                    gij += 1
                else:
                    fij = gij + 1
                    gij = 0
                res += fij
                i += 1
                j += 1
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.countSubstrings(s="aba", t="baba"))
    print(s.countSubstrings(s="abe", t="bbc"))
    print(s.countSubstrings("gtxurpxpeybugknyjbacvaxftxeyeoibplykhvqczxpbmjtrfbnzjiiwylurgpaxtfycqmrduorqhjvtfcxqdb",
                            "oysmjyvqkycewrnkodtssglydizhfzldygdcpawrbwlgcmyqnjpvjyvlddsrhkzrizqsrpmhdhytoyhwfdvkfh"))
