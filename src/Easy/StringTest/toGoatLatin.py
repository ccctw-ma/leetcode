class Solution:
    def toGoatLatin(self, sentence: str) -> str:
        # res = []
        # for index, word in enumerate(sentence.split(" ")):
        #     if word[0].lower() in ['a', 'e', 'i', 'o', 'u']:
        #         word += 'ma'
        #     else:
        #         word = word[1:] + word[0] + 'ma'
        #     word += 'a' * (index + 1)
        #     res.append(word)
        # return " ".join(res)
        return " ".join(
            [word + 'ma' + 'a' * (index + 1) if word[0].lower() in ['a', 'e', 'i', 'o', 'u'] else word[1:] + word[
                0] + 'ma' + 'a' * (index + 1) for index, word in
             enumerate(sentence.split(" "))])


if __name__ == '__main__':
    print(Solution().toGoatLatin(sentence="The quick brown fox jumped over the lazy dog"))
    print('a' * 10)
