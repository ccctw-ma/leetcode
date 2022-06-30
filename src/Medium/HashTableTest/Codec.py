import collections


class Codec:

    def __init__(self):
        self.buc = collections.defaultdict(str)

    def encode(self, longUrl: str) -> str:
        """Encodes a URL to a shortened URL.
        """
        a, b = longUrl.split("//")
        self.buc[b] = longUrl
        return b

    def decode(self, shortUrl: str) -> str:
        """Decodes a shortened URL to its original URL.
        """
        return self.buc[shortUrl]


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.decode(codec.encode(url))


if __name__ == '__main__':
    codec = Codec()
    print(codec.decode(codec.encode("https://leetcode.com/problems/design-tinyurl")))
