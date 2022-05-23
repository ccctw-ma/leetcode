from typing import List


class Solution:

    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        lefts = []
        for index, interval in enumerate(intervals):
            lefts.append((interval[0], index))
        lefts.sort()
        res = []
        for interval in intervals:
            right = interval[1]
            l, r = 0, len(lefts)
            while l < r:
                mid = (l + r) // 2
                if lefts[mid][0] >= right:
                    r = mid
                else:
                    l = mid + 1
            if l == len(lefts):
                res.append(-1)
            else:
                res.append(lefts[l][1])
        return res


if __name__ == '__main__':
    print(Solution().findRightInterval([[3, 4], [2, 3], [1, 2]]))

