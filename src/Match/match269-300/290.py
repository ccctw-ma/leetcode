import bisect
from bisect import bisect_left
from typing import List


class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        t = set(nums[0])
        for i in range(1, len(nums)):
            t &= set(nums[i])
        return sorted(t)

    def countLatticePoints(self, circles: List[List[int]]) -> int:
        s = set()
        for c in circles:
            x, y, r = c
            for i in range(x - r, x + r + 1):
                for j in range(y - r, y + r + 1):
                    dis = ((x - i) ** 2 + (y - j) ** 2)
                    if dis <= r ** 2:
                        s.add(i * 1000 + j)
        return len(s)

    def countRectangles(self, rectangles: List[List[int]], points: List[List[int]]) -> List[int]:
        # res = []
        # buc = [[] for i in range(105)]
        # for l, h in rectangles:
        #     for i in range(1, h + 1):
        #         buc[i].append(l)
        # for i in range(105):
        #     buc[i].Template()
        # for x, y in points:
        #     index = bisect.bisect_left(buc[y], x)
        #     c = len(buc[y]) - index
        #     res.append(c)
        # return res

        # buc = []
        # for rec in rectangles:
        #     buc.append(rec + [-1])
        # for i, p in enumerate(points):
        #     buc.append(p + [i])
        # buc.Template(key=lambda x: (-x[0], x[2]))
        # res = [0] * len(points)
        # ct = [0] * 101
        # for x, y, index in buc:
        #     if index == -1:
        #         for i in range(1, y + 1):
        #             ct[i] += 1
        #     else:
        #         res[index] = ct[y]
        # return res

        n = len(points)
        ans = [0] * n
        i, xs = 0, []
        for id, (x, y) in sorted(enumerate(points), key=lambda x: -x[1][1]):
            start = i
            while i < len(rectangles) and y <= rectangles[i][1]:
                xs.append(rectangles[i][0])
                i += 1
            if start < i:
                xs.sort()
            ans[id] = i - bisect_left(xs, x)
        return ans

    def fullBloomFlowers(self, flowers: List[List[int]], persons: List[int]) -> List[int]:
        buc = []
        res = [0] * len(persons)
        for fs, fe in flowers:
            buc.append((fs, 1, -1))
            buc.append((fe + 1, -1, -1))
        for i, p in enumerate(persons):
            buc.append((p, 2, i))
        buc.sort()
        c = 0
        for t, inc, i in buc:
            if i == -1:
                c += inc
            else:
                res[i] = c
        return res


if __name__ == '__main__':
    s = Solution()
    # print(s.intersection(nums=[[3, 1, 2, 4, 5], [1, 2, 3, 4], [3, 4, 5, 6]]))
    # print(s.countLatticePoints(circles=[[2, 2, 1]]))
    print(s.countRectangles(rectangles=[[1, 1], [2, 2], [3, 3]], points=[[1, 3], [1, 1]]))
    # print(s.fullBloomFlowers(flowers=[[1, 6], [3, 7], [9, 12], [4, 13]], persons=[2, 3, 7, 11]))

    # arr = [(1, 2, 3), (1, 2, 2), (1, 1, 2), (2, 2, 2), (2, 3, 3)]
    # arr = [[1, 3, 2], [1, 3, 1], [1, 2, 2], [1, 2, 1], [1, 1, 1]]
    # print(sorted(arr, reverse=True))
    # print(10 - .1)
    arr = [[1, 2], [2, 10], [2, 2], [2, 1], [3, 4]]
    print(sorted(arr, key=lambda x: (x[0], -x[1])))
