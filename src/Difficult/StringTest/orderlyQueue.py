import heapq


class Solution:
    def orderlyQueue(self, s: str, k: int) -> str:
        if k == 1:
            ans = s
            for _ in range(len(s) - 1):
                s = s[1:] + s[0]
                ans = min(ans, s)
            return ans
        return ''.join(sorted(s))

    def fn(self, sec):
        n = len(sec)
        sec = list(sec)
        k, i, j = 0, 0, 1
        while k < n and i < n and j < n:
            if sec[(i + k) % n] == sec[(j + k) % n]:
                k += 1
            else:
                if sec[(i + k) % n] > sec[(j + k) % n]:
                    i += 1
                else:
                    j += 1
                k = 0
                if i == j:
                    i += 1
        i = min(i, j)
        return sec[i:] + sec[:i]


if __name__ == '__main__':
    s = Solution()
    # print(s.orderlyQueue(s="baaca", k=3))
    # print(s.orderlyQueue("kuh", 1))
    # print('cabbd')
    """
    abbdc
    dcabb
    dabbc
    abbcd
    """
    print(s.fn('baacd'))

