import itertools
from math import perm, comb
from typing import List


class Solution:
    def minimumTimeRequired(self, jobs: List[int], k: int) -> int:
        res, n = sum(jobs), len(jobs)
        dp = [0] * k
        jobs.sort(reverse=True)

        def tb(index, limit):
            nonlocal dp, n

            if index == n:
                return True
            for i in range(k):
                if dp[i] + jobs[index] <= limit:
                    dp[i] += jobs[index]
                    if tb(index + 1, limit):
                        return True
                    dp[i] -= jobs[index]
                if dp[i] == 0 or dp[i] + jobs[index] == limit:
                    break
            return False

        def check(limit):
            nonlocal dp
            dp = [0] * k
            return tb(0, limit)

        l, r = 0, sum(jobs)
        while l < r:
            mid = (l + r) // 2
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return l


if __name__ == '__main__':
    pass
    a = 1
    print(1 << 0)
    x = j = 127
    # print(bin(x)[2:])
    while x != 0:
        print(bin(x)[2:], bin(j - x)[2:])
        x = (x - 1) & j
