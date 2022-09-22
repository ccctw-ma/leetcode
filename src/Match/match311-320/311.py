from functools import reduce, lru_cache, cache
from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def smallestEvenMultiple(self, n: int) -> int:
        return n if n % 2 == 0 else n * 2

    def longestContinuousSubstring(self, s: str) -> int:
        i, n = 0, len(s)
        res = 0
        while i < n:
            j = i + 1
            while j < n and ord(s[j]) - ord(s[j - 1]) == 1:
                j += 1
            res = max(res, j - i)
            i = j
        return res

    def reverseOddLevels(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        arr = []
        level = 0
        q = deque([root])
        while q:
            n = len(q)
            temp = []
            for i in range(n):
                node = q.popleft()
                temp.append(node)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

            if level % 2 == 1:
                for i in range(n - 1, - 1, -1):
                    arr.append(temp[i])
            else:
                arr.extend(temp)
            level += 1
        size = len(arr)
        for i in range(size):
            if i * 2 + 1 < size:
                arr[i].left = arr[i * 2 + 1]
            if i * 2 + 2 < size:
                arr[i].right = arr[i * 2 + 2]
        return arr[0]

    def sumPrefixScores(self, words: List[str]) -> List[int]:
        buc = defaultdict(int)
        for word in words:
            for i in range(1, len(word) + 1):
                pre = word[:i]
                buc[pre] += 1
        n = len(words)
        res = [0] * n
        for i, word in enumerate(words):
            for j in range(1, len(word) + 1):
                pre = word[:j]
                res[i] += buc[pre]
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.sumPrefixScores(words=["abc", "ab", "bc", "b"]))
    print(s.sumPrefixScores(words=["abcd"]))
    print(lcm(10, 9))
