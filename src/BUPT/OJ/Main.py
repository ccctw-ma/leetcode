# 打开新世界的大门，从通过这道题开始！ヽ(●´∀`●)ﾉ
#
# 输入两个整数 A, BA,B，计算 A + BA+B 的结果。
#
# 你需要写一个程序，实现从标准输入中输入两个整数 AA 和 BB，然后把 A + BA+B 的结果输出到标准输出。比如 C 语言中的标准输入输出为方法为scanf和printf，C++ 中标准输入输出的方法为cin和cout。
#
# 注意：不要输出任何多余的辅助信息，比如下面的 C 程序就是多此一举。
# A
# a = int(input())
# b = int(input())
# print(2 * b - a)

# B
# a = int(input())
# b = int(input())
# c = int(input())
# print(sorted([a, b, c])[1])

# C
# a, b, c = int(input()), int(input()), int(input())
# s = a + b + c
# if s != 180:
#     print("Error")
# elif a == b and b == c:
#     print('Equilateral')
# elif a == b or b == c or a == c:
#     print("Isosceles")
# else:
#     print("Scalene")

# D
# a, b = int(input()), int(input())
# if a < 2:
#     print("Before")
# elif a > 2:
#     print("After")
# else:
#     if b < 18:
#         print("Before")
#     elif b > 18:
#         print("After")
#     else:
#         print("Special")
from functools import lru_cache
import sys


#
# @lru_cache(maxsize=None)
# def f(m, n):
#     if m == 0:
#         return 1
#     if n == 0:
#         return 0
#     if m < n:
#         return f(m, m)
#     return f(m, n - 1) + f(m - n, n)
#
#
# t = int(input())
# for i in range(t):
#     m, n = map(int, input().split(" "))
#     print(f(m, n))


@lru_cache(None)
def f(n):
    if n <= 2:
        return n
    return max(n, f(n // 2) + f(n // 3) + f(n // 4))


value = 0
while True:
    try:
        value = int()
    except:
        break
    print(f(value))
