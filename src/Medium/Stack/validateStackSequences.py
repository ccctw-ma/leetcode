from typing import List


class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        st, j = [], 0
        for x in pushed:
            st.append(x)
            while st and st[-1] == popped[j]:
                st.pop()
                j += 1
        return len(st) == 0


if __name__ == '__main__':
    s = Solution()
    print(s.validateStackSequences(pushed=[1, 2, 3, 4, 5], popped=[4, 5, 3, 2, 1]
                                   ))
    print(s.validateStackSequences(pushed=[1, 2, 3, 4, 5], popped=[4, 3, 5, 1, 2]
                                   ))
    print(s.validateStackSequences([1, 2, 3, 4, 5, 6, 7]
                                   , [1, 2, 5, 3, 6, 7, 4]))
