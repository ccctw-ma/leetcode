from typing import List


def search(nums: List[int], target: int) -> bool:
    """
    81. 搜索旋转排序数组 II 22.3.8
    :param nums:
    :param target:
    :return:
    """
    if not nums:
        return False

    n = len(nums)
    if n == 1:
        return nums[0] == target

    l, r = 0, n - 1
    while l <= r:
        mid = (l + r) // 2
        if nums[mid] == target:
            return True
        if nums[l] == nums[mid] and nums[mid] == nums[r]:
            l += 1
            r -= 1
        elif nums[l] <= nums[mid]:
            if nums[l] <= target < nums[mid]:
                r = mid - 1
            else:
                l = mid + 1
        else:
            if nums[mid] < target <= nums[n - 1]:
                l = mid + 1
            else:
                r = mid - 1
    return False


if __name__ == '__main__':
    print(search([1, 0, 1, 1, 1], 0))

0
