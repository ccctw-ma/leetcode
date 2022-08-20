from collections import deque


class MyCircularDeque:

    def __init__(self, k: int):
        self.q = [0] * (k + 1)
        self.n = k + 1
        self.f = 0
        self.r = 0

    def insertFront(self, value: int) -> bool:
        if self.isFull():
            return False
        self.q[self.f] = value
        self.f = (self.f + 1) % self.n
        return True

    def insertLast(self, value: int) -> bool:
        if self.isFull():
            return False
        self.r = (self.r - 1 + self.n) % self.n
        self.q[self.r] = value
        return True

    def deleteFront(self) -> bool:
        if self.isEmpty():
            return False
        self.f = (self.f - 1 + self.n) % self.n
        return True

    def deleteLast(self) -> bool:
        if self.isEmpty():
            return False
        self.r = (self.r + 1) % self.n
        return True

    def getFront(self) -> int:
        return self.q[(self.f - 1 + self.n) % self.n] if not self.isEmpty() else -1

    def getRear(self) -> int:

        return self.q[self.r] if not self.isEmpty() else -1

    def isEmpty(self) -> bool:
        return self.f == self.r

    def isFull(self) -> bool:
        return (self.f + 1) % self.n == self.r


class Node:
    __slots__ = 'prev', 'next', 'val'

    def __init__(self, val):
        self.prev = self.next = None
        self.val = val


class MyCircularDeque2:
    def __init__(self, k: int):
        self.head = self.tail = None
        self.capacity = k
        self.size = 0

    def insertFront(self, value: int) -> bool:
        if self.isFull():
            return False
        node = Node(value)
        if self.isEmpty():
            self.head = node
            self.tail = node
        else:
            node.next = self.head
            self.head.prev = node
            self.head = node
        self.size += 1
        return True

    def insertLast(self, value: int) -> bool:
        if self.isFull():
            return False
        node = Node(value)
        if self.isEmpty():
            self.head = node
            self.tail = node
        else:
            self.tail.next = node
            node.prev = self.tail
            self.tail = node
        self.size += 1
        return True

    def deleteFront(self) -> bool:
        if self.isEmpty():
            return False
        self.head = self.head.next
        if self.head:
            self.head.prev = None
        self.size -= 1
        return True

    def deleteLast(self) -> bool:
        if self.isEmpty():
            return False
        self.tail = self.tail.prev
        if self.tail:
            self.tail.next = None
        self.size -= 1
        return True

    def getFront(self) -> int:
        return -1 if self.isEmpty() else self.head.val

    def getRear(self) -> int:
        return -1 if self.isEmpty() else self.tail.val

    def isEmpty(self) -> bool:
        return self.size == 0

    def isFull(self) -> bool:
        return self.size == self.capacity


if __name__ == '__main__':
    q = MyCircularDeque(3)
    print(q.insertLast(1))
    print(q.insertLast(2))
    print(q.insertFront(3))
    print(q.insertFront(4))
    print(q.getRear())
    print(q.isFull())
    print(q.deleteLast())
    print(q.insertFront(4))
    print(q.getFront())
