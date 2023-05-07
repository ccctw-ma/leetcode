import time, re
from string import ascii_uppercase, ascii_lowercase, digits
from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter, OrderedDict
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise, groupby
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist, sqrt
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right, insort, insort_left, insort_right


class DinnerPlates:
    def __init__(self, capacity: int):
        self.capacity = capacity  # 栈的容量
        self.stacks = []  # 所有栈
        self.h = []  # 最小堆，保存未满栈的下标

    def push(self, val: int) -> None:
        if self.h and self.h[0] >= len(self.stacks):
            self.h = []  # 堆中都是越界下标，直接清空
        if self.h:  # 还有未满栈
            self.stacks[self.h[0]].append(val)  # 入栈
            if len(self.stacks[self.h[0]]) == self.capacity:  # 栈满了
                heappop(self.h)  # 从堆中去掉
        else:  # 所有栈都是满的
            self.stacks.append([val])  # 添加一个新的栈
            if self.capacity > 1:  # 新的栈没有满
                heappush(self.h, len(self.stacks) - 1)  # 入堆

    def pop(self) -> int:
        # 等价为 popAtStack 最后一个非空栈
        return self.popAtStack(len(self.stacks) - 1)

    def popAtStack(self, index: int) -> int:
        if index < 0 or index >= len(self.stacks) or len(self.stacks[index]) == 0:
            return -1  # 非法操作
        if len(self.stacks[index]) == self.capacity:  # 满栈
            heappush(self.h, index)  # 元素出栈后，栈就不满了，把下标入堆
        val = self.stacks[index].pop()
        while self.stacks and len(self.stacks[-1]) == 0:
            self.stacks.pop()  # 去掉末尾的空栈（懒删除，堆中下标在 push 时处理）
        return val


class DinnerPlates2:

    def __init__(self, capacity: int):
        self.stk = []
        self.c = capacity
        self.delpos = []
        self.dpset = set()

    def push(self, val: int) -> None:
        while self.delpos:
            pos = heappop(self.delpos)
            if pos in self.dpset:
                self.dpset.remove(pos)
                self.stk[pos] = val
                return
        self.stk.append(val)

    def pop(self) -> int:
        while len(self.stk) - 1 in self.dpset:
            self.dpset.remove(len(self.stk) - 1)
            self.stk.pop()
        if not self.stk:
            return -1
        return self.stk.pop()

    def popAtStack(self, index: int) -> int:
        offset = index * self.c
        if offset >= len(self.stk):
            return -1
        pos = min(len(self.stk), offset + self.c - 1)
        while pos >= offset and pos in self.dpset:
            pos -= 1
        if pos < offset:
            return -1
        heappush(self.delpos, pos)
        self.dpset.add(pos)
        return self.stk[pos]

class DinnerPlates3:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.stack = []
        self.top = []
        self.poppedPos = SortedSet()

    def push(self, val: int) -> None:
        if not self.poppedPos:
            pos = len(self.stack)
            self.stack.append(val)
            if pos % self.capacity == 0:
                self.top.append(0)
            else:
                stackPos = len(self.top) - 1
                stackTop = self.top[stackPos]
                self.top[stackPos] = stackTop + 1
        else:
            pos = self.poppedPos.pop(0)
            self.stack[pos] = val
            index = pos // self.capacity
            stackTop = self.top[index]
            self.top[index] = stackTop + 1

    def pop(self) -> int:
        while self.stack and self.poppedPos and self.poppedPos[-1] == len(self.stack) - 1:
            self.stack.pop()
            pos = self.poppedPos.pop()
            if pos % self.capacity == 0:
                self.top.pop()
        if not self.stack:
            return -1
        else:
            pos = len(self.stack) - 1
            val = self.stack[pos]
            self.stack.pop()
            if pos % self.capacity == 0 and self.top:
                self.top.pop()
            elif self.top:
                self.top[-1] -= 1
            return val

    def popAtStack(self, index: int) -> int:
        if index >= len(self.top):
            return -1
        stackTop = self.top[index]
        if stackTop < 0:
            return -1
        self.top[index] = stackTop - 1
        pos = index * self.capacity + stackTop
        self.poppedPos.add(pos)
        return self.stack[pos]

# Your DinnerPlates object will be instantiated and called as such:
# obj = DinnerPlates(capacity)
# obj.push(val)
# param_2 = obj.pop()
# param_3 = obj.popAtStack(index)
if __name__ == '__main__':
    # s = Solution()
    d = DinnerPlates(2)
    d.push(1)
    d.push(2)
    d.push(3)
    d.push(4)
    d.push(5)
    print(d.popAtStack(0))
    d.push(20)
    d.push(21)
    print(d.popAtStack(0))
    print(d.popAtStack(2))
    print(d.pop())
    print(d.pop())
    print(d.pop())
    print(d.pop())
    print(d.pop())
