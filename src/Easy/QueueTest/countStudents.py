from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class Solution:
    def countStudents(self, students: List[int], sandwiches: List[int]) -> int:
        q = deque(students)
        index = 0
        while True:
            a = sum(q)
            if len(q) == 0:
                break
            if a == 0 and sandwiches[index] == 1 or a == len(q) and sandwiches[index] == 0:
                break
            if q[0] == sandwiches[index]:
                q.popleft()
                index += 1
            else:
                q.append(q.popleft())

        return len(q)


if __name__ == '__main__':
    s = Solution()
    print(s.countStudents(students=[1, 1, 0, 0], sandwiches=[0, 1, 0, 1]))
