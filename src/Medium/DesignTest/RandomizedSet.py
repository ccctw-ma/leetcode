import math
import random

import mpmath


class RandomizedSet:

    def __init__(self):
        self.nums = []
        self.indexs = {}

    def insert(self, val: int) -> bool:
        if val in self.indexs:
            return False
        else:
            self.indexs[val] = len(self.nums)
            self.nums.append(val)
            return True

    def remove(self, val: int) -> bool:
        if val in self.indexs:
            index = self.indexs[val]
            self.nums[index] = self.nums[-1]
            self.indexs[self.nums[-1]] = index
            self.nums.pop()
            del self.indexs[val]
            return True
        else:
            return False

    def getRandom(self) -> int:
        return random.choice(self.nums)


# Your RandomizedSet object will be instantiated and called as such:
if __name__ == '__main__':
    obj = RandomizedSet()
    param_1 = obj.insert(1)
    param_2 = obj.remove(2)
    print(obj.getRandom())
