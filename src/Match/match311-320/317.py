from typing import List, Optional, Union, Set
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd, lcm
from functools import lru_cache, cache, reduce

from src.Difficult.UnionFind.largestIsland import TreeNode


class Solution:
    def averageValue(self, nums: List[int]) -> int:
        arr = []
        for i in nums:
            if i % 3 == 0 and i % 2 == 0:
                arr.append(i)
        return sum(arr) // len(arr) if len(arr) != 0 else 0

    def mostPopularCreator(self, creators: List[str], ids: List[str], views: List[int]) -> List[List[str]]:
        buc = defaultdict(int)
        bb = defaultdict(list)
        for a, b, c in zip(creators, ids, views):
            buc[a] += c
            bb[a].append((-c, b))
        max_view = max(buc.values())
        res = []
        for k, v in buc.items():
            if v == max_view:
                arr = bb[k]
                arr.sort()
                res.append([k, arr[0][1]])
        return res

    def makeIntegerBeautiful(self, n: int, target: int) -> int:
        def change(n):
            arr = list(map(int, str(n)))[::-1]
            arr += [0]
            s = sum(arr)
            return arr, s

        arr = list(map(int, str(n)))[::-1]
        arr += [0]
        print(arr)
        pre = sum(arr)
        if pre <= target:
            return 0
        count = 0
        nn = len(arr)
        for i in range(nn):
            add = (10 - arr[i]) * (10 ** i)
            count += add
            aa, ss = change(n + add)
            if ss <= target:
                return count
            n += add
            arr = aa
        return count

    def treeQueries(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:

        buc = defaultdict(int)
        levels = defaultdict(int)
        levelHeap = defaultdict(list)

        def dfs(root, level):
            if root is None:
                return 0
            levels[root.val] = level
            a = dfs(root.left, level + 1)
            b = dfs(root.right, level + 1)
            c = max(a, b) + 1
            buc[root.val] = c
            levelHeap[level].append(-c)
            return c

        dfs(root, 1)

        res = []
        cacheRes = defaultdict(int)
        for q in queries:
            if q in cacheRes:
                res.append(cacheRes[q])
                continue
            l = levels[q]
            val = buc[q]
            temp = levelHeap[l]
            if len(temp) == 1:
                res.append(l - 2)
                continue
            arr = temp.copy()
            heapify(arr)
            if arr[0] == -val:
                heappop(arr)
                res.append(l - arr[0] - 2)
            else:
                # 不造成影响
                res.append(buc[root.val] - 1)
            cacheRes[q] = res[-1]
        return res



if __name__ == '__main__':
    s = Solution()
    print(s.makeIntegerBeautiful(n=467, target=6))
    print(s.makeIntegerBeautiful(n=99999999, target=1))
