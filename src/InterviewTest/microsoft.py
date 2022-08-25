# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")
import math
from collections import defaultdict


def solution(S):
    # write your code in Python (Python 3.6)

    dp = [0] * 10
    for c in S:
        dp[ord(c) - ord('0')] += 1

    middle = ''
    part = ''
    for i in range(9, -1, -1):
        if dp[i] % 2 == 1 and len(middle) == 0:
            middle = str(i)
        n = dp[i] // 2
        part += str(i) * n

    if len(part) == 0 and len(middle) == 1:
        return middle
    if int(part) == 0 and len(middle) == 0:
        return '0'
    if int(part) == 0 and middle != '0':
        return middle
    return part + middle + ''.join(reversed(list(part)))


#
# print(solution("39878"))
# print(solution("00900"))
# print(solution('0000'))
# print(solution('54321'))


def solution(A, B):
    # write your code in Python (Python 3.6)

    graph = defaultdict(list)
    for x, y in zip(A, B):
        graph[x].append(y)
        graph[y].append(x)
    visited = set()
    visited.add(0)
    res = 0

    def bfs(index):
        nonlocal visited, graph, res
        if len(graph[index]) == 1 and graph[index][0] in visited:
            return 1

        ps = 0
        for c in graph[index]:
            if c not in visited:
                visited.add(c)
                p = bfs(c)
                ps += p
                res += math.ceil(p / 5)
        return ps + 1
    bfs(0)
    return res


print(solution([1, 1, 1, 9, 9, 9, 9, 7, 8], [2, 0, 3, 1, 6, 5, 4, 0, 0]))
# print(math.ceil(7 / 5))
