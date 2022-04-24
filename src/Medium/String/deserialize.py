# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
import collections


class NestedInteger(object):
    def __init__(self, value=None):
        """
        If value is not specified, initializes an empty list.
        Otherwise initializes a single integer equal to value.
        """

    def isInteger(self):
        """
        @return True if this NestedInteger holds a single integer, rather than a nested list.
        :rtype bool
        """

    def add(self, elem):
        """
        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
        :rtype void
        """

    def setInteger(self, value):
        """
        Set this NestedInteger to hold a single integer equal to value.
        :rtype void
        """

    def getInteger(self):
        """
        @return the single integer that this NestedInteger holds, if it holds a single integer
        Return None if this NestedInteger holds a nested list
        :rtype int
        """

    def getList(self):
        """
        @return the nested list that this NestedInteger holds, if it holds a nested list
        Return None if this NestedInteger holds a single integer
        :rtype List[NestedInteger]
        """


# "[123,456,[788,799,833],[[]],10,[]]"
class Solution(object):
    def deserialize(self, s):
        nestInteger = NestedInteger()
        if len(s) == 0:
            return nestInteger
        if s[0] != "[":
            nestInteger.setInteger(int(s))
        else:
            innerS = s[1: len(s) - 1]
            left = 0
            while left < len(innerS):
                right = left
                if innerS[right] != '[':
                    while right < len(innerS) and innerS[right] != ',':
                        right += 1
                else:
                    cnt = 1
                    while right < len(innerS) and cnt != 0:
                        if innerS[right] == '[':
                            cnt += 1
                        elif innerS[right] == ']':
                            cnt -= 1
                        right += 1
                    right += 1
                temp = self.deserialize(innerS[left: right])
                nestInteger.add(temp)
                left = right + 1
        return nestInteger


if __name__ == '__main__':
    # print(Solution().deserialize(s="[123,[456,[789]]]"))
    index = 2
    print(eval("[123,[456,[789]]]"))
    print(eval("index ** 10"))
    print(eval("[123,456,[788,799,833],[[]],10,[]]"))
    print("abcd"[0:index])
