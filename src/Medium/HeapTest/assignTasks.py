from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
# from math import perm, comb
from typing import List, Optional
from functools import lru_cache


# from functools import cache
class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        h1, h2 = [], []
        for i, server in enumerate(servers):
            h1.append((server, i))
        heapq.heapify(h1)
        res = [0] * len(tasks)
        for t, task in enumerate(tasks):
            while h2 and h2[0][0] <= t:
                e, s, i = heapq.heappop(h2)
                heapq.heappush(h1, (s, i))
            if h1:
                s, i = heapq.heappop(h1)
                res[t] = i
                heapq.heappush(h2, (t + task, s, i))
            else:
                e, s, i = heapq.heappop(h2)
                res[t] = i
                heapq.heappush(h2, (e + task, s, i))
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.assignTasks(servers=[3, 3, 2], tasks=[1, 2, 3, 2, 1, 2]))
    # print(s.assignTasks([5, 1, 4, 3, 2],
    #                     [2, 1, 2, 4, 5, 2, 1]))
    print(s.assignTasks([10, 63, 95, 16, 85, 57, 83, 95, 6, 29, 71],
                        [70, 31, 83, 15, 32, 67, 98, 65, 56, 48, 38, 90, 5]))
