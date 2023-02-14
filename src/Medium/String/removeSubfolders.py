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


class Trie:

    def __init__(self):
        self.children = {}
        self.isEnd = False

    def insert(self, arr) -> None:
        node = self
        for ch in arr:
            if ch not in node.children:
                node.children[ch] = Trie()

            node = node.children[ch]
        node.isEnd = True


class Solution:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        folder.sort(key=lambda x: len(x))

        trie = Trie()

        res = []
        for fold in folder:
            fs = fold.split("/")[1:]
            trie.insert(fs)

        def dfs(t, s):
            if t.isEnd:
                res.append(s)
                return
            for k in t.children:
                dfs(t.children[k], s + "/" + k)

        dfs(trie, "")
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.removeSubfolders(folder=["/a", "/a/b", "/c/d", "/c/d/e", "/c/f"]))
    print(s.removeSubfolders(folder=["/a/b/c", "/a/b/ca", "/a/b/d"]
                             ))
    print(s.removeSubfolders(folder=["/a", "/a/b/c", "/a/b/d"]))
