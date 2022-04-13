import random


class RandomizedCollection:

    def __init__(self):
        self.nums = []
        self.indexs = {}

    def insert(self, val: int) -> bool:
        if val in self.indexs:
            self.indexs[val].add(len(self.nums))
            self.nums.append(val)
            return False
        else:
            self.indexs[val] = {len(self.nums)}
            self.nums.append(val)
            return True

    def remove(self, val: int) -> bool:
        if val not in self.indexs:
            return False
        else:
            index = self.indexs[val].pop()
            end = self.nums[-1]
            end_index = len(self.nums) - 1
            self.nums[index] = end
            self.nums.pop()
            self.indexs[end].add(index)
            self.indexs[end].remove(end_index)
            if len(self.indexs[val]) == 0:
                del self.indexs[val]
            return True

    def getRandom(self) -> int:
        return random.choice(self.nums)


# Your RandomizedCollection object will be instantiated and called as such:

if __name__ == '__main__':
    obj = RandomizedCollection()
    print(obj.insert(1))
    print(obj.remove(1))
    print(obj.insert(1))
    # print(obj.getRandom())

