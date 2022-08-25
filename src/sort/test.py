import collections
import functools
import time

import sortedcontainers


# 1 1 2 3 5 8 13 21
class Fib:
    def __init__(self):
        self.count_recursion = 0
        self.count_loop = 0

    # 递归的方式 时间复杂度O(2^n) 空间复杂度O（n）
    def fibonacci_recursion(self, n: int) -> int:
        self.count_recursion += 1
        if n <= 2:
            return 1
        return self.fibonacci_recursion(n - 1) + self.fibonacci_recursion(n - 2)

    # 循环的方式 时间复杂度O(n) 空间复杂度O（1）
    def fibonacci_loop(self, n: int) -> int:
        a, b = 1, 1
        if n <= 2:
            return 1
        for i in range(3, n + 1):
            self.count_loop += 1
            a, b = a + b, a
        return a


if __name__ == '__main__':
    F = Fib()
    s = time.time()
    print(F.fibonacci_recursion(20))
    print(F.fibonacci_loop(20))
    print(f'递归的执行次数：{F.count_recursion}')
    print(f'循环的执行次数：{F.count_loop}')
