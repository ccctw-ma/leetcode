from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Trie:

    def __init__(self):
        self.children = [None] * 26
        self.isEnd = False

    def insert(self, s: str) -> None:
        node = self
        for ch in s:
            index = ord(ch) - ord('a')
            if not node.children[index]:
                node.children[index] = Trie()

            node = node.children[index]
        node.isEnd = True

    def searchPrefix(self, prefix: str) -> Optional["Trie"]:
        node = self
        for ch in prefix:
            index = ord(ch) - ord('a')
            if not node.children[index]:
                return None
            node = node.children[index]
        return node


class Solution:
    def numMatchingSubseq(self, s: str, words: List[str]) -> int:

        p = defaultdict(list)
        for i, w in enumerate(words):
            p[w[0]].append((i, 0))
        ans = 0
        for c in s:
            q = p[c]
            p[c] = []
            for i, j in q:
                j += 1
                if j == len(words[i]):
                    ans += 1
                else:
                    p[words[i][j]].append((i, j))
        return ans


if __name__ == '__main__':
    s = Solution()
    ss = "abcdefghijklmnopqrstuvwxyz"
    print(ss.find("ijkl"))