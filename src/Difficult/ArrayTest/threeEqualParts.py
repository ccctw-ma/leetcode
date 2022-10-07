from typing import List


# 1的个数必须是3的整数倍 将1等分成3份 再根据最后一份末尾0的个数推导 前两份的最后一个坐标，最后判断3份去掉前导0 是不是一样
class Solution:
    def threeEqualParts2(self, arr: List[int]) -> List[int]:
        n = len(arr)
        ones = sum(arr)
        if ones % 3 != 0:
            return [-1, -1]
        if ones == 0:
            return [0, 2]
        ones //= 3
        rearZeros, rear = 0, n - 1
        # 求最后一个数的后置0的个数
        while rear >= 0 and arr[rear] == 0:
            rear -= 1
            rearZeros += 1

        index, count = 0, 0
        while index < n and count != ones:
            count += arr[index]
            index += 1
        if sum(arr[index: index + rearZeros]) == 0:
            i = index + rearZeros - 1
        else:
            return [-1, -1]
        index, count = i + 1, 0
        while index < n and count != ones:
            count += arr[index]
            index += 1
        if sum((arr[index: index + rearZeros])) == 0:
            j = index + rearZeros
        else:
            return [-1, -1]
        a = arr[:i + 1]
        b = arr[i + 1: j]
        c = arr[j:]
        a = int(''.join(map(str, a)))
        b = int(''.join(map(str, b)))
        c = int(''.join(map(str, c)))
        return [i, j] if a == b == c else [-1, -1]


if __name__ == '__main__':
    s = Solution()
    print(s.threeEqualParts(arr=[1, 0, 1, 0, 1]))
    print(s.threeEqualParts(arr=[1, 1, 0, 0, 1]))
    arr = [1, 2, 3]
    print(arr[0:0])
    print(sum([]))
