from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb
from typing import List, Optional


class Solution:
    def minimumTeachings(self, n: int, languages: List[List[int]], friendships: List[List[int]]) -> int:
        buc = defaultdict(set)
        for i, language in enumerate(languages):
            for j in language:
                buc[i + 1].add(j)
        arr = []
        for a, b in friendships:
            if len(buc[a] & buc[b]) == 0:
                arr.append([a, b])
        lan = set()
        user = set()
        for a, b in arr:
            user.add(a)
            user.add(b)
            for c in (buc[a] ^ buc[b]):
                lan.add(c)
        res = 0
        for l in lan:
            count = 0
            for u in user:
                if l not in buc[u]:
                    count += 1
            res = min(res, count)
        return res

    def minimumTeachings2(self, n: int, languages: List[List[int]], friendships: List[List[int]]) -> int:
        languages = {i + 1: set(language) for i, language in enumerate(languages)}
        connections = defaultdict(list)  # connections that cannot conmunicate
        for u, v in friendships:
            if not languages[u] & languages[v]:
                connections[u].append(v)
                connections[v].append(u)
        lang_freq = defaultdict(int)
        popularity = 0
        for i in connections:
            for l in languages[i]:
                lang_freq[l] += 1
                popularity = max(lang_freq[l], popularity)
        return len(connections) - popularity


if __name__ == '__main__':
    s = Solution()
    print(s.minimumTeachings(n=2, languages=[[1], [2], [1, 2]], friendships=[[1, 2], [1, 3], [2, 3]]))
    print(s.minimumTeachings(n=3, languages=[[2], [1, 3], [1, 2], [3]], friendships=[[1, 4], [1, 2], [3, 4], [2, 3]]
                             ))
    print(s.minimumTeachings(11,
                             [[3, 11, 5, 10, 1, 4, 9, 7, 2, 8, 6], [5, 10, 6, 4, 8, 7], [6, 11, 7, 9], [11, 10, 4],
                              [6, 2, 8, 4, 3], [9, 2, 8, 4, 6, 1, 5, 7, 3, 10], [7, 5, 11, 1, 3, 4],
                              [3, 4, 11, 10, 6, 2, 1, 7, 5, 8, 9], [8, 6, 10, 2, 3, 1, 11, 5], [5, 11, 6, 4, 2]]
                             , [[7, 9], [3, 7], [3, 4], [2, 9], [1, 8], [5, 9], [8, 9], [6, 9], [3, 5], [4, 5], [4, 9],
                                [3, 6], [1, 7], [1, 3], [2, 8], [2, 6], [5, 7], [4, 6], [5, 8], [5, 6], [2, 7], [4, 8],
                                [3, 8], [6, 8], [2, 5], [1, 4], [1, 9], [1, 6], [6, 7]]))
