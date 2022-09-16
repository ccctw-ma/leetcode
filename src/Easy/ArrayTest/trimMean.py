from typing import List


class Solution:
    def trimMean(self, arr: List[int]) -> float:
        arr.sort()
        n = len(arr)
        remove = n // 20
        arr = arr[remove:-remove]
        return sum(arr) / (n - 2 * remove)


if __name__ == '__main__':
    s = Solution()
    print(s.trimMean())
