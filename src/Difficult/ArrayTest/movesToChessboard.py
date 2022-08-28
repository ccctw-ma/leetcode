from typing import List


# 太难 不会
class Solution:
    def movesToChessboard(self, board: List[List[int]]) -> int:
        n = len(board)
        one = sum(sum(row) for row in board)
        zero = n ** 2 - one
        if abs(one - zero) > 1:
            return -1


if __name__ == '__main__':
    s = Solution()
    print(s.movesToChessboard(board=[[0, 1, 1, 0], [0, 1, 1, 0], [1, 0, 0, 1], [1, 0, 0, 1]]))
