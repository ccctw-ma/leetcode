from typing import List


def countMaxOrSubsets(nums: List[int]) -> int:
    max_val = 0
    for num in nums:
        max_val |= num
    res = 0
    for index in range(1, 1 << len(nums)):
        temp = 0
        for i in range(len(nums)):
            if index & (1 << i):
                temp |= nums[i]
        if temp == max_val:
            res += 1
    return res


def countMaxOrSubsets2(nums: List[int]) -> int:
    res = [0]
    for num in nums:
        res += [num | n for n in res]
    return res.count(max(res))


if __name__ == '__main__':
    # res = (1 << 16) - 1

    print(countMaxOrSubsets(nums=[2, 2, 2]))
