import math
import random
from typing import List


class Solution:

    def __init__(self, radius: float, x_center: float, y_center: float):
        self.xc = x_center
        self.yc = y_center
        self.r = radius

    def randPoint(self) -> List[float]:
        u, theta = random.random(), random.random() * 2 * math.pi
        r = math.sqrt(u)
        return [self.xc + r * math.cos(theta) * self.r, self.yc + r * math.sin(theta) * self.r]


if __name__ == '__main__':
    obj = Solution(1, 0.0, 0.0)
    param_1 = obj.randPoint()
    print(param_1)
    # print(random.uniform(0, 360))
    # print(math.cos(math.pi * 2))
    print(random.random())
