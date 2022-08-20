from itertools import permutations
from typing import List


class Solution:
    def validSquare(self, p1: List[int], p2: List[int], p3: List[int], p4: List[int]) -> bool:
        def check(p1, p2, p3, p4):
            mid1 = [p1[0] + p2[0], p1[1] + p2[1]]
            mid2 = [p3[0] + p4[0], p3[1] + p4[1]]
            if mid1[0] == mid2[0] and mid1[1] == mid2[1]:
                dis1 = (p2[0] - p1[0]) ** 2 + (p2[1] - p1[1]) ** 2
                dis2 = (p4[0] - p3[0]) ** 2 + (p4[1] - p3[1]) ** 2
                if dis1 == dis2 and dis1 != 0:
                    return (p4[1] - p3[1]) * (p2[1] - p1[1]) + (p4[0] - p3[0]) * (p2[0] - p1[0]) == 0
            return False

        ps = [p1, p2, p3, p4]
        arr = [0, 1, 2, 3]
        for a, b, c, d in permutations(arr):
            if check(ps[a], ps[b], ps[c], ps[d]):
                return True
        return False


if __name__ == '__main__':
    s = Solution()
    print(s.validSquare(p1=[0, 0], p2=[1, 1], p3=[1, 0], p4=[0, 1]))
