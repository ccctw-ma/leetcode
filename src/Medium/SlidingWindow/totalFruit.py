from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        buc = defaultdict(int)
        res = 1
        index = 0
        n = len(fruits)
        max_window = 0
        # 初始化最大窗口
        while index < n:
            f = fruits[index]
            if f not in buc and len(buc.keys()) == 2:
                break
            buc[fruits[index]] += 1
            max_window += 1
            index += 1

        for i in range(n):
            if i + max_window >= n:
                break
            rear = i
            front = i + max_window
            buc[fruits[rear]] -= 1
            buc[fruits[front]] += 1
            if buc[fruits[rear]] == 0:
                del buc[fruits[rear]]
            j = front + 1
            while j < n and len(buc.keys()) <= 2:
                f = fruits[j]
                if f not in buc and len(buc.keys()) == 2:
                    break
                buc[f] += 1
                max_window += 1
                j += 1
        return max_window


if __name__ == '__main__':
    s = Solution()
    print(s.totalFruit([5, 0, 0, 7, 0, 7, 2, 7]))
    print(s.totalFruit(fruits=[1, 2, 1]))
    print(s.totalFruit(fruits=[0, 1, 2, 2]))
    print(s.totalFruit(fruits=[1, 2, 3, 2, 2]))
    print(s.totalFruit(fruits=[3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4]))
