import collections


class Solution:
    def greatestLetter(self, s: str) -> str:
        res = ""
        buc = set()
        for c in s:
            buc.add(c)
            if c.upper() in buc and c.lower() in buc:
                if res == "" or ord(c.upper()) > ord(res):
                    res = c.upper()
        return res

    def minimumNumbers(self, num: int, k: int) -> int:

        if num == 0:
            return 0
        if num % 10 == k:
            return 1

        d = num % 10
        res = -1
        for i in range(1, 11):
            if (k * i) % 10 == d and num >= k * i:
                res = i

        return res

    def longestSubsequence(self, s: str, k: int) -> int:

        ct = collections.Counter(s)
        to_ret = ct['0']
        vt = 0
        n = 1
        for c in s[::-1]:
            c = int(c)
            vt = vt + c * n
            n = n * 2
            if vt > k:
                break
            if c == 1:
                to_ret += 1
        return to_ret


if __name__ == '__main__':
    S = Solution()
    # print(ord('a'))

    # print(S.minimumNumbers(10, 8))

    print(S.longestSubsequence("001010101011010100010101101010010", 93951055))

    # print(S.longestSubsequence(s="00101001", k=1))
