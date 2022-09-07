import bisect
from typing import List
import heapq
from collections import defaultdict, deque
from itertools import accumulate
from math import perm, comb
from typing import List, Optional

import sortedcontainers


class Solution:
    def checkDistances(self, s: str, distance: List[int]) -> bool:
        dp = [-1] * 26
        buc = defaultdict(int)
        for i, c in enumerate(s):
            if c in buc:
                dp[ord(c) - ord('a')] = i - buc[c] - 1
            else:
                buc[c] = i
        for i in range(26):
            if dp[i] != -1:
                if dp[i] != distance[i]:
                    return False
        return True

    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        mod = 10 ** 9 + 7
        dp = [0] * 3005
        dp[startPos + 1000] = 1
        for i in range(k):
            temp = [0] * 3005
            for j in range(3000):
                if dp[j]:
                    temp[j - 1] += dp[j]
                    temp[j + 1] += dp[j]
            dp = temp
        return dp[endPos + 1000] % mod

    def longestNiceSubarray(self, nums: List[int]) -> int:
        res = 1
        l, r = 0, 0
        n = len(nums)
        while l < n:
            temp = nums[l]
            r = l + 1
            while r < n and nums[r] & temp == 0:
                temp |= nums[r]
                r += 1
            res = max(res, r - l)
            l += 1
        return res

    def mostBooked(self, n: int, meetings: List[List[int]]) -> int:
        # meetings.sort()
        # dp = [0] * n
        # ends = [0] * n
        # for i in range(len(meetings)):
        #     start, end = meetings[i]
        #     minIndex = 0
        #     tar = -1
        #
        #     for i in range(n):
        #         endTime = ends[i]
        #         if ends[i] < ends[minIndex]:
        #             minIndex = i
        #         if endTime <= start:
        #             tar = i
        #             break
        #     if tar == -1:
        #         tarIndex = minIndex
        #     else:
        #         tarIndex = tar
        #
        #     minEnd = ends[tarIndex]
        #     if minEnd <= start:
        #         ends[minIndex] = end
        #     else:
        #         ends[minIndex] += (end - start)
        #     dp[minIndex] += 1
        #
        # return dp.index(max(dp))

        roomCanUse = list(range(n))
        heapq.heapify(roomCanUse)
        used = []
        meetings.sort()
        count = [0] * n
        for start, end in meetings:
            while used and used[0][0] <= start:
                heapq.heappush(roomCanUse, used[0][1])
                heapq.heappop(used)
            if roomCanUse:
                index = heapq.heappop(roomCanUse)
                count[index] += 1
                heapq.heappush(used, (end, index))
            else:
                oldEnd, index = used[0]
                heapq.heappop(used)
                count[index] += 1
                heapq.heappush(used, (oldEnd + end - start, index))

        return count.index(max(count))


if __name__ == '__main__':
    s = Solution()
    # print(s.numberOfWays(10, 113,
    #                      1000))

    # print(s.longestNiceSubarray(nums=[1, 3, 8, 48, 10]))
    # print(s.longestNiceSubarray(
    #     [84139415, 693324769, 614626365, 497710833, 615598711, 264, 65552, 50331652, 1, 1048576, 16384, 544, 270532608,
    #      151813349, 221976871, 678178917, 845710321, 751376227, 331656525, 739558112, 267703680]))
    # print(s.mostBooked(n=3, meetings=[[1, 20], [2, 10], [3, 5], [4, 9], [6, 8]]))
    print(s.mostBooked(4,
                       [[18, 19], [3, 12], [17, 19], [2, 13], [7, 10]]))
