import collections
from typing import List, Union


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        def diffOne(a, b) -> bool:
            return sum(x != y for x, y in zip(a, b)) == 1

        m = len(wordList)
        adj = [[] for _ in range(m)]
        endIndex = -1
        for i, a in enumerate(wordList):
            if a == endWord:
                endIndex = i
            for j in range(i + 1, m):
                if diffOne(a, wordList[j]):
                    adj[i].append(j)
                    adj[j].append(i)
        if endIndex == -1:
            return 0
        q = [i for i, s in enumerate(wordList) if diffOne(s, beginWord)]
        vis = set(q)
        step = 2
        while q:
            temp = []
            for i in q:
                if wordList[i] == endWord:
                    return step
                for j in adj[i]:
                    if j not in vis:
                        temp.append(j)
                        vis.add(j)
            q = temp
            step += 1
        return 0

    def ladderLength2(self, beginWord: str, endWord: str, wordList: List[str]) -> Union[int, float]:
        def addWord(word: str):
            if word not in wordId:
                nonlocal nodeNum
                wordId[word] = nodeNum
                nodeNum += 1

        def addEdge(word: str):
            addWord(word)
            id1 = wordId[word]
            chars = list(word)
            for i in range(len(chars)):
                tmp = chars[i]
                chars[i] = "*"
                newWord = "".join(chars)
                addWord(newWord)
                id2 = wordId[newWord]
                edge[id1].append(id2)
                edge[id2].append(id1)
                chars[i] = tmp

        wordId = dict()
        edge = collections.defaultdict(list)
        nodeNum = 0

        for word in wordList:
            addEdge(word)

        addEdge(beginWord)
        if endWord not in wordId:
            return 0

        disBegin = [float("inf")] * nodeNum
        beginId = wordId[beginWord]
        disBegin[beginId] = 0
        queBegin = collections.deque([beginId])

        disEnd = [float("inf")] * nodeNum
        endId = wordId[endWord]
        disEnd[endId] = 0
        queEnd = collections.deque([endId])

        while queBegin or queEnd:
            queBeginSize = len(queBegin)
            for _ in range(queBeginSize):
                nodeBegin = queBegin.popleft()
                if disEnd[nodeBegin] != float("inf"):
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) // 2 + 1
                for it in edge[nodeBegin]:
                    if disBegin[it] == float("inf"):
                        disBegin[it] = disBegin[nodeBegin] + 1
                        queBegin.append(it)

            queEndSize = len(queEnd)
            for _ in range(queEndSize):
                nodeEnd = queEnd.popleft()
                if disBegin[nodeEnd] != float("inf"):
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) // 2 + 1
                for it in edge[nodeEnd]:
                    if disEnd[it] == float("inf"):
                        disEnd[it] = disEnd[nodeEnd] + 1
                        queEnd.append(it)

        return 0


if __name__ == '__main__':
    print(Solution().ladderLength(beginWord="hit", endWord="cog", wordList=["hot", "dot", "dog", "lot", "log", "cog"]
                                  ))
