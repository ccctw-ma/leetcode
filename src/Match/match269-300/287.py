import collections
from bisect import bisect_left
from collections import Counter
from typing import List


class Solution:
    def convertTime(self, current: str, correct: str) -> int:
        cur_hour, cur_min = map(int, current.split(":"))
        cor_hour, cor_min = map(int, correct.split(":"))
        minutes = (cor_hour * 60 + cor_min - cur_hour * 60 - cur_min + 24 * 60) % (24 * 60)
        res = 0
        res += minutes // 60
        minutes %= 60
        res += minutes // 15
        minutes %= 15
        res += minutes // 5
        minutes %= 5
        return res + minutes

    def findWinners(self, matches: List[List[int]]) -> List[List[int]]:
        bucket = [100005] * 100005
        for match in matches:
            winner, loser = match
            if bucket[winner] == 100005:
                bucket[winner] = 0
            if bucket[loser] == 100005:
                bucket[loser] = -1
            else:
                bucket[loser] -= 1
        ans1, ans2 = [], []
        for index, num in enumerate(bucket):
            if num != 100005:
                if num == 0:
                    ans1.append(index)
                elif num == -1:
                    ans2.append(index)
        return [ans1, ans2]
        # c = Counter(m[1] for m in matches)
        # return sorted(({m[0] for m in matches} | {m[1] for m in matches}) - c.keys()), sorted(
        #     {k for k, v in c.items() if v == 1})

    def maximumCandies(self, candies: List[int], k: int) -> int:
        # def check(candies, k, length, num) -> bool:
        #     if k <= length and candies[length - k] >= num:
        #         return True
        #     for i in range(length - 1, -1, -1):
        #         k -= candies[i] // num
        #         if k <= 0:
        #             return True
        #     return False
        #
        # candies.sort()
        # length = len(candies)
        # l, r = 0, candies[length - 1]
        # while l < r:
        #     mid = (l + r + 1) // 2
        #     flag = check(candies, k, length, mid)
        #     if flag:
        #         l = mid
        #     else:
        #         r = mid - 1
        # return l

        get = lambda t: sum(x // t for x in candies) < k
        return bisect_left(range(1, max(candies) + 1), True, key=get)


class Encrypter:

    def __init__(self, keys: List[str], values: List[str], dictionary: List[str]):
        self.en = dict()
        self.cnt = collections.defaultdict(int)
        for i, key in enumerate(keys):
            self.en[key] = values[i]
        for s in dictionary:
            self.cnt[self.encrypt(s)] += 1

    def encrypt(self, word1: str) -> str:
        s = ""
        for c in word1:
            s += self.en[c]
        return s

    def decrypt(self, word2: str) -> int:
        return self.cnt[word2]


if __name__ == '__main__':
    solution = Solution()
    # print(solution.convertTime(current="11:00", correct="10:59"))
    # print(solution.findWinners(matches=[[2, 3], [1, 3], [5, 4], [6, 4]]))
    # print(solution.maximumCandies([1, 2, 3, 4, 10], 5))
    encrypter = Encrypter(['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"],
                          ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"])
    print(encrypter.encrypt("abcd"))
    print(encrypter.decrypt("eizfeiam"))
