class Node:

    def __init__(self, val, pre=None, next=None):
        self.val = val
        self.next = next
        self.pre = pre


class MyLinkedList:

    def __init__(self):
        self.head = Node(-1)
        self.rear = Node(1001)
        self.head.next = self.rear
        self.rear.pre = self.head
        self.size = 0

    def get(self, index: int) -> int:
        if index < 0 or index >= self.size:
            return -1
        temp = self.head.next
        for i in range(index):
            temp = temp.next
        return temp.val

    def addAtHead(self, val: int) -> None:
        next = self.head.next
        temp = Node(val, self.head, next)
        self.head.next = temp
        next.pre = temp
        self.size += 1

    def addAtTail(self, val: int) -> None:
        pre = self.rear.pre
        temp = Node(val, pre, self.rear)
        pre.next = temp
        self.rear.pre = temp
        self.size += 1

    def addAtIndex(self, index: int, val: int) -> None:
        if index > self.size:
            return
        elif index <= 0:
            self.addAtHead(val)
        elif index == self.size:
            self.addAtTail(val)
        else:
            temp = self.head.next
            for i in range(index):
                temp = temp.next
            node = Node(val, temp.pre, temp)
            temp.pre.next = node
            temp.pre = node
            self.size += 1

    def deleteAtIndex(self, index: int) -> None:
        if index < 0 or index >= self.size:
            return
        temp = self.head.next
        for i in range(index):
            temp = temp.next
        temp.pre.next = temp.next
        temp.next.pre = temp.pre
        self.size -= 1


if __name__ == '__main__':
    mll = MyLinkedList()
    mll.addAtHead(1)
    mll.addAtTail(3)
    mll.addAtIndex(1, 2)
    print(mll.get(1))
    mll.deleteAtIndex(1)
    print(mll.get(1))
