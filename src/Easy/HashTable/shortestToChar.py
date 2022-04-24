from typing import List


class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        l, r = float('-inf'), 0
        n = len(s)
        res = [0] * n
        while r < n and s[r] != c:
            r += 1
        index = 0
        while index < n:
            while index < n and s[index] != c:
                res[index] = min(abs(index - l), abs(r - index))
                index += 1
            l = r
            index = l + 1
            r += 1
            while r < n and s[r] != c:
                r += 1
            if r == n:
                r = float('inf')
        return res


if __name__ == '__main__':
    print(Solution().shortestToChar(s="loveleetcode", c="e"))
