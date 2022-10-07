from typing import List, Tuple, Union
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress
from math import perm, comb, gcd, lcm
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right


class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.persons = persons
        self.times = times
        buc = defaultdict(int)
        max_vote, p = 0, 0
        self.tp = defaultdict(int)
        for person, time in zip(persons, times):
            buc[person] += 1
            if buc[person] >= max_vote:
                max_vote = buc[person]
                p = person
            self.tp[time] = p

    def q(self, t: int) -> int:
        return self.tp[self.times[bisect_right(self.times, t) - 1]]


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)


if __name__ == '__main__':
    s = TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30])
    print(s.q(3))
