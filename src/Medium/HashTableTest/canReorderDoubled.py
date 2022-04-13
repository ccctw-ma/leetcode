from collections import Counter
from typing import List


class Solution:
    def canReorderDoubled(self, arr: List[int]) -> bool:
        arr.sort()
        length = len(arr)
        bucket = [0] * 200001
        for num in arr:
            if -50000 <= num <= 50000 and bucket[2 * num + 100000]:
                bucket[2 * num + 100000] -= 1
                length -= 2
            elif num % 2 == 0 and bucket[num // 2 + 100000]:
                bucket[num // 2 + 100000] -= 1
                length -= 2
            else:
                bucket[num + 100000] += 1
        return length == 0

    def canReorderDoubled2(self, arr: List[int]) -> bool:
        cnt = Counter(arr)
        if cnt[0] % 2:
            return False
        for x in sorted(cnt, key=abs):
            if cnt[2 * x] < cnt[x]:  # 无法找到足够的 2x 与 x 配对
                return False
            cnt[2 * x] -= cnt[x]
        return True


if __name__ == '__main__':
    s = Solution()
    print(s.canReorderDoubled2(arr=[4, -2, 2, -4]))
    arr = [1, 2, 3, 4, 5, 67, 7, 89]
    arr.sort(key=str, reverse=True)
    print(arr)
