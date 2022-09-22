from functools import reduce, lru_cache, cache
from typing import List
import heapq
from collections import defaultdict, deque, Counter
from itertools import accumulate
from math import perm, comb, gcd
from typing import List, Optional


class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str) -> int:
        months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

        def fn(month, day):
            count = 0
            for i in range(month - 1):
                count += months[i]
            return count + day

        a = fn(int(arriveAlice[:2]), int(arriveAlice[3:]))
        b = fn(int(leaveAlice[:2]), int(leaveAlice[3:]))
        c = fn(int(arriveBob[:2]), int(arriveBob[3:]))
        d = fn(int(leaveBob[:2]), int(leaveBob[3:]))
        a, b = sorted([[a, b], [c, d]])
        if a[1] < b[0]:
            return 0
        s = max(a[0], b[0])
        e = min(a[1], b[1])
        return e - s + 1

    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort(reverse=True)
        trainers.sort(reverse=True)
        m, n = len(players), len(trainers)
        res = 0
        i, j = 0, 0
        while i < m and j < n:
            if players[i] <= trainers[j]:
                i += 1
                j += 1
                res += 1
            else:
                i += 1

        return res

    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        b = [0 for i in range(32)]
        n = len(nums)
        z = [0 for i in range(n)]
        for i in range(n)[::-1]:
            for j in range(32):
                if nums[i] >> j & 1:
                    b[j] = i
            z[i] = max(max(b) - i + 1, 1)
        return z

    def minimumMoney(self, transactions: List[List[int]]) -> int:
        m, s = 0, 0
        for a, b in transactions:
            m = max(m.min(a, b))
            s += max(0, a - b)
        return s + m


if __name__ == '__main__':
    s = Solution()
    # print(s.countDaysTogether(arriveAlice="08-15", leaveAlice="08-18", arriveBob="08-16", leaveBob="08-19"))
    # print(s.countDaysTogether(arriveAlice="10-01", leaveAlice="10-31", arriveBob="11-01", leaveBob="12-31"))
    print(s.matchPlayersAndTrainers([4, 7, 9],
                                    [8, 2, 5, 8]))
