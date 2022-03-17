from typing import List


def bestRotation(nums: List[int]) -> int:
    # 暴力超时
    # length = len(nums)
    # min_index = 0
    # max_score = 0
    # for k in range(length):
    #     temp = nums[k:] + nums[:k]
    #     score = 0
    #     for i, num in enumerate(temp):
    #         if num <= i:
    #             score += 1
    #     if score > max_score:
    #         max_score = score
    #         min_index = k
    # return min_index
    pass


if __name__ == '__main__':
    print(bestRotation([2, 3, 1, 4, 0]))
    arr = [1, 2, 3, 4, 5, 6]
    print(arr[:0])
    print(arr[3:])
