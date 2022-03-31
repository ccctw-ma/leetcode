import collections


class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        l, r, t, f, ans = 0, 0, 0, 0, 0
        for r in range(len(answerKey)):
            if answerKey[r] == "T":
                t += 1
            else:
                f += 1
            while t > k and f > k:
                if answerKey[l] == "T":
                    t -= 1
                else:
                    f -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans


if __name__ == '__main__':
    solution = Solution()
    print(solution.maxConsecutiveAnswers(answerKey="TTFF", k=2))

    print(1 + False)
