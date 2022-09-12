from typing import List


class Solution:
    def constructArray(self, n: int, k: int) -> List[int]:
        if k == 1:
            return list(range(1, n + 1))
        l, r = 1, n
        res = [l]
        l += 1
        flag = True
        for i in range(1, n):
            if flag:
                res.append(r)
                r -= 1
            else:
                res.append(l)
                l += 1
            if i < k - 1:
                flag = not flag
        return res


if __name__ == '__main__':
    s = Solution()
    for k in range(1, 10):
        print(s.constructArray(n=10, k=k))
