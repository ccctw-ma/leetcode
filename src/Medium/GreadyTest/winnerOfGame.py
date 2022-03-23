class Solution:
    def winnerOfGame(self, colors: str) -> bool:
        alice, bob = 0, 0
        for i in range(1, len(colors) - 1):
            a = colors[i - 1]
            b = colors[i]
            c = colors[i + 1]
            if b == 'A' and a == c and a == 'A':
                alice += 1
            if b == 'B' and a == c and a == 'B':
                bob += 1
        return bool(alice) and alice > bob


if __name__ == '__main__':
    solution = Solution()
    print(solution.winnerOfGame(colors="AAAABBBB"))
