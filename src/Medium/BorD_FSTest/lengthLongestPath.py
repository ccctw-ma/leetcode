class Solution:

    def lengthLongestPath(self, input: str) -> int:

        def isFile(s):
            return '.' in s

        def parse(s):
            count, index = 0, 0
            while index + 1 < len(s) and s[index:index + 1] == '\t':
                count += 1
                index += 1
            return [count, s[index:]]

        max_length = 0
        arr = input.split("\n")
        pre = 0
        stack = []
        for s in arr:
            level, s = parse(s)
            while level < len(stack):
                pre -= stack.pop()

            if isFile(s):
                max_length = max(max_length, pre + len(s))
            else:
                stack.append(len(s) + 1)
                pre += len(s) + 1

        return max_length


if __name__ == '__main__':
    # input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
    # print(input.split("\n"))
    # print(input)
    # print("hello".split("\n"))
    print(Solution().lengthLongestPath(
        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"))
