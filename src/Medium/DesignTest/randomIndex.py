import collections
import random
from typing import List


class Solution:

    def __init__(self, nums: List[int]):
        self.buc = collections.defaultdict(list)
        for i, num in enumerate(nums):
            self.buc[num].append(i)

    def pick(self, target: int) -> int:
        arr = self.buc[target]
        return random.choice(arr)


if __name__ == '__main__':
    obj = Solution([1, 2, 3, 3, 3])
    pa1 = obj.pick(3)
    print(pa1)

# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.pick(target)
