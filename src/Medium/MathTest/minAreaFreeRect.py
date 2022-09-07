import collections
import itertools
import math
from itertools import combinations, product, accumulate
from typing import List


class Solution:
    def minAreaFreeRect(self, points: List[List[int]]) -> float:
        res = math.inf

        for a, b, c, d in list(combinations(range(len(points)), 4)):
            a, b, c, d = points[a], points[b], points[c], points[d]
            a, b, c, d = sorted([a, b, c, d])
            ax, ay = a
            bx, by = b
            cx, cy = c
            dx, dy = d
            # 是个菱形
            if ax + dx == bx + cx and ay + dy == by + cy:
                d1 = (ax - dx) ** 2 + (ay - dy) ** 2
                d2 = (bx - cx) ** 2 + (by - cy) ** 2
                # 是个矩形
                if d1 == d2 and d1 != 0:
                    width = (ax - bx) ** 2 + (ay - by) ** 2
                    height = (ax - cx) ** 2 + (ay - cy) ** 2
                    area = math.sqrt(width * height)
                    res = min(res, area)
        return 0 if res == math.inf else res

    def minAreaFreeRect2(self, points):
        points = [complex(*z) for z in points]
        seen = collections.defaultdict(list)
        for P, Q in itertools.combinations(points, 2):
            center = (P + Q) / 2
            radius = abs(center - P)
            seen[center, radius].append(P)

        ans = float("inf")
        for (center, radius), candidates in seen.items():
            for P, Q in itertools.combinations(candidates, 2):
                ans = min(ans, abs(P - Q) * abs(P - (2 * center - Q)))

        return ans if ans < float("inf") else 0


if __name__ == '__main__':
    s = Solution()
    print(s.minAreaFreeRect([[0, 1], [2, 1], [1, 1], [1, 0], [2, 0]]))
    print(s.minAreaFreeRect([[3, 1], [1, 1], [0, 1], [2, 1], [3, 3], [3, 2], [0, 2], [2, 3]]))
    points = [[3, 1], [1, 1], [0, 1], [2, 1], [3, 3], [3, 2], [0, 2], [2, 3]]
    points = [complex(*z) for z in points]
    print(points)
