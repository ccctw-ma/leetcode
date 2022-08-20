import collections


class MovingAverage:

    def __init__(self, size: int):
        """
        Initialize your data structure here.
        """
        self.size = size
        self.queue = collections.deque()
        self.count = 0

    def next(self, val: int) -> float:
        self.queue.append(val)
        self.count += val
        if len(self.queue) > self.size:
            self.count -= self.queue.popleft()

        return self.count / len(self.queue)

# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
