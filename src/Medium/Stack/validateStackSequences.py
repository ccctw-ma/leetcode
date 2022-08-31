from typing import List


class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        pushIdx, popIdx = 0, 0
        stk = []
        n = len(pushed)
        while pushIdx < n:
            # 入栈
            while pushIdx < n and popIdx < n and pushed[pushIdx] != popped[popIdx]:
                stk.append(pushed[pushIdx])
                pushIdx += 1
            pushIdx += 1
            popIdx += 1
            # 出栈
            while stk and popIdx < n and stk[-1] == popped[popIdx]:
                stk.pop()
                popIdx += 1

        return len(stk) == 0


if __name__ == '__main__':
    s = Solution()
    print(s.validateStackSequences(pushed=[1, 2, 3, 4, 5], popped=[4, 5, 3, 2, 1]
                                   ))
    print(s.validateStackSequences(pushed=[1, 2, 3, 4, 5], popped=[4, 3, 5, 1, 2]
                                   ))
    print(s.validateStackSequences([1, 2, 3, 4, 5, 6, 7]
                                   , [1, 2, 5, 3, 6, 7, 4]))
