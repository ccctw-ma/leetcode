from typing import List


class Solution:
    def validUtf8(self, data: List[int]) -> bool:
        length = len(data)
        index = 0
        while index < length:
            mask = 1 << 7
            bit_count = 0
            first = data[index]
            while mask & first:
                bit_count += 1
                mask >>= 1
            if bit_count == 1 or bit_count > 4:
                return False
            bit_count = bit_count if bit_count > 0 else 1
            mask1 = 1 << 7
            mask2 = 1 << 6
            if index + bit_count > length:
                return False
            for i in range(index + 1, index + bit_count):
                if not (data[i] & mask1 and not (data[i] & mask2)):
                    return False
            index = index + bit_count
        return True


if __name__ == '__main__':
    s = Solution()
    print(bin(250)[2:])
    print(bin(145)[2:])
    print(s.validUtf8([250, 145, 145, 145, 145]))
