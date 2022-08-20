import _heapq
import heapq
from collections import Counter
from typing import List


class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        return [item[0] for i, item in enumerate(sorted(Counter(words).items(), key=lambda x: (-x[1], x[0]))) if i < k]


if __name__ == '__main__':
    s = Solution()
    # print(s.topKFrequent(["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k=4))
    # print(s.topKFrequent(["i", "love", "leetcode", "i", "love", "coding"]
    #                      , 3))
    #
    # arr = ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"]
    # print(heapq.nlargest(2, arr, key=lambda x: len(x)))

    arr = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]
    _heapq.heapify(arr)
    print(heapq.heappop(arr))
    print(heapq.nlargest(4, arr, key=lambda x: x % 4))
