import bisect
from typing import List


class Solution:

    def numTeams(self, rating: List[int]) -> int:

        def get(arr, x):
            c = 0
            while x > 0:
                c += arr[x]
                x -= x & (-x)
            return c

        def add(arr, x):
            while x < len(arr):
                arr[x] += 1
                x += x & (-x)

        buc = sorted(rating)
        n = len(rating)
        maxN = n + 2
        arr = [0] * maxN
        iLess = [0] * n
        iMore = [0] * n
        kLess = [0] * n
        kMore = [0] * n
        for i in range(n):
            index = bisect.bisect(buc, rating[i])
            iLess[i] = get(arr, index)
            iMore[i] = get(arr, n + 1) - get(arr, index)
            add(arr, index)
        arr = [0] * maxN
        for i in range(n - 1, -1, -1):
            index = bisect.bisect(buc, rating[i])
            kLess[i] = get(arr, index)
            kMore[i] = get(arr, n + 1) - get(arr, index)
            add(arr, index)
        res = 0
        for i in range(n):
            res += iLess[i] * kMore[i] + iMore[i] * kLess[i]

        return res


if __name__ == '__main__':
    print(Solution().numTeams(rating=[2, 5, 3, 4, 1]))
