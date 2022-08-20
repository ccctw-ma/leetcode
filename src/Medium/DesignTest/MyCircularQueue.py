class MyCircularQueue:

    def __init__(self, k: int):
        self.q = [0] * (k + 1)
        self.f = 0
        self.r = 0
        self.n = k + 1

    def enQueue(self, value: int) -> bool:
        if self.isFull():
            return False
        self.q[self.r] = value
        self.r = (self.r + 1) % self.n
        return True

    def deQueue(self) -> bool:
        if self.isEmpty():
            return False
        self.f = (self.f + 1) % self.n
        return True

    def Front(self) -> int:
        return -1 if self.isEmpty() else self.q[self.f]

    def Rear(self) -> int:
        return -1 if self.isEmpty() else self.q[(self.r - 1 + self.n) % self.n]

    def isEmpty(self) -> bool:
        return self.f == self.r

    def isFull(self) -> bool:
        return (self.r + 1) % self.n == self.f


if __name__ == '__main__':
    # obj = MyCircularQueue(k)
    # param_1 = obj.enQueue(value)
    # param_2 = obj.deQueue()
    # param_3 = obj.Front()
    # param_4 = obj.Rear()
    # param_5 = obj.isEmpty()
    # param_6 = obj.isFull()
    pass
