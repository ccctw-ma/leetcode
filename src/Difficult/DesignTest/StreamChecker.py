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
        self.children = [None] * 26
        self.isEnd = False

    def insert(self, s: str) -> None:
        node = self
        for ch in s:
            idx = ord(ch) - ord('a')
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.isEnd = True


class StreamChecker:

    def __init__(self, words: List[str]):
        self.trie = Trie()
        self.pointers = deque()
        for word in words:
            self.trie.insert(word)

    def query(self, letter: str) -> bool:
        index = ord(letter) - ord('a')
        n = len(self.pointers)
        for _ in range(n):
            tmp = self.pointers.popleft()
            if tmp.children[index]:
                self.pointers.append(tmp.children[index])
        if self.trie.children[index]:
            self.pointers.append(self.trie.children[index])
        return any(node.isEnd for node in self.pointers)


# Your StreamChecker object will be instantiated and called as such:
# obj = StreamChecker(words)
# param_1 = obj.query(letter)


if __name__ == '__main__':
    # s = Solution()
    streamChecker = StreamChecker(["cd", "f", "kl"])
    print(streamChecker.query("a"))
    print(streamChecker.query("b"))
    print(streamChecker.query("c"))
    print(streamChecker.query("d"))
    print(streamChecker.query("e"))
    print(streamChecker.query("f"))
    print(streamChecker.query("g"))
    print(streamChecker.query("h"))
    print(streamChecker.query("i"))
    print(streamChecker.query("j"))
    print(streamChecker.query("k"))
    print(streamChecker.query("l"))
