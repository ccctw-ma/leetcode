from typing import List


class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        length = len(parents)
        bucket = [[-1, -1, 0, 0] for _ in range(length)]
        for i, p in enumerate(parents):
            if p != -1:
                if bucket[p][0] == -1:
                    bucket[p][0] = i
                else:
                    bucket[p][1] = i

        def dfs(root) -> int:
            if bucket[root][0] == -1 and bucket[root][1] == -1:
                return 1
            left, right = 0, 0
            if bucket[root][0] != -1:
                left = dfs(bucket[root][0])
            if bucket[root][1] != -1:
                right = dfs(bucket[root][1])
            bucket[root][2] = left
            bucket[root][3] = right
            return left + right + 1

        dfs(0)
        # print(bucket)

        max_score = 0
        count = 0
        for i in range(length):
            left_size = bucket[i][2]
            right_size = bucket[i][3]
            parent_size = length - left_size - right_size - 1
            if left_size == 0:
                left_size = 1
            if right_size == 0:
                right_size = 1
            if parent_size == 0:
                parent_size = 1
            temp = left_size * right_size * parent_size
            if temp > max_score:
                max_score = temp
                count = 1
            elif temp == max_score:
                count += 1

        return count

    def countHighestScoreNodes2(self, parents: List[int]) -> int:
        n = len(parents)
        children = [[] for _ in range(n)]
        for node, p in enumerate(parents):
            if p != -1:
                children[p].append(node)

        max_score, cnt = 0, 0

        def dfs(node: int) -> int:
            score = 1
            size = n - 1
            for ch in children[node]:
                sz = dfs(ch)
                score *= sz
                size -= sz
            if node != 0:
                score *= size
            nonlocal max_score, cnt
            if score == max_score:
                cnt += 1
            elif score > max_score:
                max_score, cnt = score, 1
            return n - size

        dfs(0)
        return cnt


if __name__ == '__main__':
    solution = Solution()
    print(solution.countHighestScoreNodes(parents=[-1, 2, 0, 2, 0]))
    print(solution.countHighestScoreNodes(parents=[-1, 2, 0]))
    arr = [2, 3, 4, 5]
    # for i, a in enumerate(arr):
    #     print(i, a)
