from typing import List


def removeDuplicates(nums: List[int]) -> int:
    """
    写复杂了
    :param nums:
    :return:
    """
    length = len(nums)
    left = 0
    while left < length:
        r = left
        while r < length and nums[r] == nums[left]:
            r += 1
        width = r - left
        if width > 2:
            remove = width - 2
            for i in range(r, length):
                nums[i - remove] = nums[i]
            length -= remove
        left += width if width <= 2 else 2
    return length


def removeDuplicates2(nums: List[int]) -> int:
    length = len(nums)
    if length <= 2:
        return length
    index = 0
    for i in range(length):
        if i < 2 or nums[i] != nums[index - 2]:
            nums[index] = nums[i]
            index += 1
    return index


if __name__ == '__main__':
    arr = [1, 1, 1, 2, 2, 3, 3, 3, 3, 3];
    length = removeDuplicates2(arr)
    print(length)
    for i in range(length):
        print(arr[i], end="\t")
