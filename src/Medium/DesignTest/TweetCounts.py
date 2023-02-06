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


class TweetCounts:

    def __init__(self):
        self.buc = defaultdict(SortedList)
        self.freqM = {
            "minute": 60,
            "hour": 3600,
            "day": 86400
        }

    def recordTweet(self, tweetName: str, time: int) -> None:
        self.buc[tweetName].add(time)

    def getTweetCountsPerFrequency(self, freq: str, tweetName: str, startTime: int, endTime: int) -> List[int]:
        freq = self.freqM[freq]
        arr = self.buc[tweetName]

        res = []
        start = startTime
        while start <= endTime:
            a, b = start, min(endTime, start + freq - 1)
            ia, ib = arr.bisect_left(a - 1), arr.bisect_right(b)
            res.append(ib - ia)
            start = b + 1
        return res

    # Your TweetCounts object will be instantiated and called as such:


# obj = TweetCounts()
# obj.recordTweet(tweetName,time)
# param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime)

if __name__ == '__main__':
    tc = TweetCounts()
    tc.recordTweet('a', 0)
    tc.recordTweet('a', 60)
    tc.recordTweet('a', 10)
    print(tc.getTweetCountsPerFrequency('minute', 'a', 0, 59))
    print(tc.getTweetCountsPerFrequency('minute', 'a', 0, 60))
    print(tc.getTweetCountsPerFrequency('minute', 'a', 20, 30))
    tc.recordTweet('a', 120)
    print(tc.getTweetCountsPerFrequency('hour', 'a', 0, 210))
