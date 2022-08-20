from typing import List


class Solution:
    def intersectionSizeTwo(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        ans, n, m = 0, len(intervals), 2
        vals = [[] for _ in range(n)]
        for i in range(n - 1, -1, -1):
            j = intervals[i][0]
            for k in range(len(vals[i]), m):
                ans += 1
                for p in range(i - 1, -1, -1):
                    if intervals[p][1] < j:
                        break
                    vals[p].append(j)
                j += 1
        return ans

    def intersectionSizeTwo2(self, intervals: List[List[int]]) -> int:

        intervals.sort(key=lambda x: (x[1], x[0]))

        ans = 2  # 第一个区间需要
        end = intervals[0][1]
        pre_end = end - 1  # 贪心地将pre_end设置为end-1

        for x, y in intervals[1:]:

            if x <= pre_end:  # x <= pre_end < end <= y【无需添加元素】
                continue

            if x <= end:  # pre_end < x <= end <= y【只需添加y为end】
                ans += 1
                pre_end = end
                end = y
            else:  # end < x < y【需添加y为end，y-1为pre_end】
                ans += 2
                pre_end = y - 1  # 贪心地将pre_end设置为y-1
                end = y

        return ans


if __name__ == '__main__':
    s = Solution()
    # print(s.intersectionSizeTwo(intervals=[[1, 3], [1, 4], [2, 5], [3, 5]]))
    # print(s.intersectionSizeTwo(intervals=[[1, 2], [2, 3], [2, 4], [4, 5]]))
    # print(s.intersectionSizeTwo2(
    #     [[2, 10], [3, 7], [3, 15], [4, 11], [6, 12], [6, 16], [7, 8], [7, 11], [8, 11], [9, 11], [7, 15], [11, 12]]))
    print(s.intersectionSizeTwo2(
        [[2, 10], [3, 7], [3, 15], [4, 11], [6, 12], [6, 16], [7, 8], [7, 11], [8, 11], [9, 11], [7, 15], [11, 12]]))
