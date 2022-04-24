import collections
from typing import List


class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        # l, r = 0, 0
        # paragraph = paragraph.lower()
        # while r < len(paragraph) and not paragraph[r].islower():
        #     r += 1
        # l = r
        #
        # arr = []
        # while l < len(paragraph):
        #     r = l
        #     while r < len(paragraph) and paragraph[r].islower():
        #         r += 1
        #     arr.append(paragraph[l:r])
        #     while r < len(paragraph) and not paragraph[r].islower():
        #         r += 1
        #     l = r
        # cnt = collections.Counter(arr)
        # banned = set(banned)
        # for key, count in cnt.most_common():
        #     if key not in banned:
        #         return key
        # return ""
        for c in "!?',;.":
            paragraph = paragraph.replace(c, " ")
        return collections.Counter(list(filter(lambda x: x.lower() not in banned, paragraph.split()))).most_common(1)[0][0]


if __name__ == '__main__':
    print(
        Solution().mostCommonWord(paragraph="Bob hit a ball, the hit BALL flew far after it was hit.", banned=["hit"]))
    # print("abc.".upper())
    # print(ord('z'))
