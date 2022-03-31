class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        temp = n ^ (n >> 1)
        return (temp & (temp + 1)) == 0


if __name__ == '__main__':
    a, b = 0, 0
    for i in range(1, 32, 2):
        a |= (1 << i)
    for i in range(0, 32, 2):
        b |= (1 << i)
    solution = Solution()
    print(solution.hasAlternatingBits(2))
