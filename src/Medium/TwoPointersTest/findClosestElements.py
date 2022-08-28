import bisect
from typing import List


class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        index = bisect.bisect_left(arr, x)
        l, r = index - 1, index
        for _ in range(k):
            if l < 0:
                r += 1
            elif r == len(arr) or x - arr[l] <= arr[r] - x:
                l -= 1
            else:
                r += 1

        return arr[l + 1: r]


if __name__ == '__main__':
    arr = [1, 2, 3, 4, 5]
    # print(bisect.bisect_left(arr, 0))
    s = Solution()
    print(s.findClosestElements(arr, 4, -1))

    arr.sort(key=lambda v: abs(v - 3))
    print(arr)
