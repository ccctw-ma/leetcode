from typing import List, Tuple, Union, Optional
from heapq import heappop, heappush, heapify, heappushpop, heapreplace
from collections import defaultdict, deque, Counter
from itertools import accumulate, permutations, combinations, product, compress, zip_longest, pairwise
from math import perm, comb, gcd, lcm, inf, ceil, floor, factorial, dist
from functools import cache, lru_cache, reduce
from sortedcontainers import SortedList, SortedSet, SortedDict
from bisect import bisect_left, bisect_right

from src.Difficult.UnionFind.largestIsland import TreeNode


class Solution:
    def convertTemperature(self, celsius: float) -> List[float]:
        return [celsius + 273.15, celsius * 1.8 + 32.0]

    def subarrayLCM(self, nums: List[int], k: int) -> int:

        res = 0
        n = len(nums)
        for i in range(n):
            pre = nums[i]
            for j in range(i, n):
                pre = lcm(pre, nums[j])
                if pre == k:
                    res += 1
                if pre > k:
                    break
        return res

    def minimumOperations(self, root: Optional[TreeNode]) -> int:
        res = 0
        q = deque([root])
        while q:
            n = len(q)
            temp = []
            for i in range(n):
                node = q.popleft()
                temp.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            count = 0
            arr = sorted(temp)
            buc = {c: i for i, c in enumerate(temp)}
            for i in range(len(temp)):
                if temp[i] != arr[i]:
                    tar = buc[arr[i]]
                    temp[i], temp[tar] = temp[tar], temp[i]
                    buc[temp[i]] = i
                    buc[temp[tar]] = tar
                    count += 1
            res += count
        return res

    def maxPalindromes(self, s: str, k: int) -> int:

        n = len(s)

        # @cache
        # def f(i, j):
        #     if i >= j:
        #         return True
        #     return f(i + 1, j - 1) and s[i] == s[j]

        f = [[False] * n for _ in range(n)]
        for i in range(n):
            for j in range(i + 1):
                f[i][j] = True
        for j in range(n):
            for i in range(j - 1, -1, -1):
                if s[i] == s[j] and f[i + 1][j - 1]:
                    f[i][j] = True
        # print(f)

        dp = [0] * (n + 1)
        for i in range(k, n + 1):
            for span in range(k, i + 1):
                if dp[i - span] + 1 < dp[i]:
                    break
                # temp = s[i - span: i]
                # print(temp)
                # print(i - span, i - 1)
                # print(f[i - span][i - 1])
                # if f(i - span, i - 1):
                if f[i - span][i - 1]:
                    dp[i] = max(dp[i], dp[i - span] + 1)

            dp[i] = max(dp[i], dp[i - 1])
        # print(dp)
        return dp[n]


if __name__ == '__main__':
    s = Solution()
    # print(s.subarrayLCM(nums=[3, 6, 2, 7, 1], k=6))
    print(s.maxPalindromes(s="abaccdbbd", k=3))
    # print(s.maxPalindromes(s='aaaaaaaaa', k=2))
    # print(s.maxPalindromes(s="adbcda", k=2))
    print(s.maxPalindromes(
        "yxexpkpbfaiqytmfwzxcufhvlgmxyapqaejxqoqxjeaqpayxmglvhfucxzwfmtyqiafbpkpxehdxerpcgdxugmgaudhsickoefcwtbhtokszzskothbtwcfeokcishduagmguxdgcpjgxftmzqapdqpswuhzdcwuylljkoyvgrnnggnnrgvyokjllyuwcdzhuwspqdpaqzmtfcljluzpooawetqavvbypuszaponhujyhzilmddmlizhyjuhnopazsupybvvaqtewaoopzuljlcaxpgraewdohlgajnndvazddlmbsuafausbmlddzavdnnjaglhodweargpxarnjxjnxfzuzhfybqqltvnifunhdayqphvkkvhpqyadhnufinvtlqqbyfhzuzfxnjxjnrzchxvdebzoubojgmlicnglsnegjnrdlpfoeermzzxveuxxuevxzzmreeofpldrnjgenslgncilmgjobuozbedvxxvndgcaamsjdamubyfddpbbpddfybumadjsmaacgdnvxrwdmasrrnrsaydewtcibwddaxqqqqxaddwbictwedyasrnrrsamdwrnrbuybqdmclvhfnjxhdvltlskadtdaksltlvdhxjnfhvlcmdqbyubrngrmbjsqiiamrqmxxfsypoiuqomvhoioctchevvehctcoiohvmoquiopysfxxmqrmaiiqsjbmawmilevepcdvvslddcdqaundtonqcwqjggjqwcqnotdnuaqdcddlsvvdcpevelimwaeavjqovdywlzohzlplrttrlplzhozlwydvoqjvaeaigksxwttrqyypxtlnwipyjjniqfhzwalobcnzsimuumiszncbolawzhfqinjjypiwnltxpyyqrttwxskgvrwufedgizojqulyaqtmljnibwwpwcomyoradhbbaclaialcabbhdaroymocwpwwbinjlmtqayluqjozclzzsraavewhdfivosatopfzecqpivmyjooeoojymvipqcezfpotasovifdhwevaarszzlbgewmpblmzyneowgpilbyivwusxelvvlexsuwviyblipgwoenyzmlbpmwegbaccfcbrnhzniknjzecjkzockgcyqrwwrqycgkcozkjcezjnkinzhnrbcfccarhfvvynhohpyyemdqbraxbkqkenftlsuzugllzorhayahrozllguzusltfnekqkbxarbqdmeyyphorxlqxxshtdpcbbhirlqohrpijbliylbyaccwvxahjxmaahwdktsstkdwhaamxjhaxvwccayblyilbjiprhoqlrihbbwxfcpwspcyoytcatbfthfldccdimleczkvnhrvdbkbdvrhnvkzcelmidccdlfhtfbtactyoycpswpctdcggtzfytmedbaocqarwqptyiokvtpcsqkhylqlyhkqscptvkoiytpqwraqcoabdemtyfztggcdtmczholcvcievrfsblwsbcbmhauljywmjrcghnlirgfgrilnhgcrjmwyjluahmbcbswlbsfrveicvclohzcmncuygkxvifvvipgrhbrjmwecekcrydgglgkjuujkglggdyrckecewmjrbhrgpivvfivxkgylafdrhtbrrrldebevdrbpahixkussukxihapbrdvebedlrrrbterzzpcmbkoyfmdccaigozixhkhbdnbcvacntrzzzzzzrtncavcbndbhkhxizogiaccdmfyokbmcpzzrkjnduvzzmj"
        , 36))
    print(s.maxPalindromes(
        "ebdvblyklgbzzbaxoqjfifvjtjsbsyvwquzuggcvrbuwuwswaeeawswuwubrvcgguzuqwvysbsjtjvfifjqoxabzzbglkylbvdbexugfzsyekpgluiphdvcgzugngxxvnexaqgrjfwjgtzwlcdzrkjjkrzdclwztgjwfjrgqaxenvxxgnguzgcvdhpiulgpkeyszfguxxftaofjhcefizqowiokvuwmrvllefpsrzzfknlfdfexejojyrewweryjojexefdflnkfzzrspfellvrmwuvkoiwoqzifechjfoatffuatsvrygvkwezlmiuzqfpjprgnpoyuqthidfluvpunmqjsseyadffdayessjqmnupvulfdihtquyopngrpjpfqzuimlzewkvgyrvstsmoizvqougeadnignaykwrnltbwechdlcvxdcocyiqkwfsvxcawhkheaipwxwwxwpiaehkhwacxvsfwkqiycocdxvcldhcewbtlnrwkyangindaxlmedtenwnvknnztgefujupqksnufyjduxlcnstuqyawirjylaalyjriwayqutsnclxudjyfunskqpujufegtznnkvnwnetdemlxgvqiiefzxnryldjbrtsyommcxbkwyqeehllydkapljxabvaalmoomlaavbaxjlpakdyllheeqywkbxcmmoystrbjdlyrnxzfeiiqvmvocawvcockptzalrpvzoylfngcykknvgwkxttbvtumlrtypiggipytrlmutvbttxkwgvnkkycgnflyozvprlaztpkcocvwacovmfggyweaoaxjnixkbfyoptxaflykygldlmfqqvgpdnaawwprsazzasrpwwaandpgvqqfmldlgykylfaxtpoyfbkxinjxaoaewyggfksynvaxyepakwsbopomsmpbbfjeiohnupmcmgnurghlzpeejmhhmjeepzlhgrungmcmpunhoiejfbbpmsmopobswkapeyxavnyskvepelbtsjegkihootobomdmiwpesmezeebuhccmnvgeaymwocrtkjhljjlhjktrcowmyaegvnmcchubeezemsepwimdmobotoohikgejstofycpsyakmwktiupfqlbkusrqvqeyudsqmjvqqxvtogllcsxqmmqxscllgotvxqqvjmqsduyeqvqrsukblqfpuitkwmkayspcyfoemxqqnddkrenirbonlcluidpoktfjzxvdppwlsjvitodccbvntyytnvbccdotivjslwppdvxzjftkopdiulclnobrinerkddnqqxmgydjohenkjbhsddcydezjjhuyukmaycumushzouipkuidkzmdfoofdmzkdiukpiuozhsumucyamkuyuhjjzedycddshbjknehojdynruwgfkonmclvhfluqzuhsjhvmxojvedvcqlejphuqjnjxdahtffthadxjnjquhpjelqcvdevjoxmvhjshuzqulfhvlcmnokfgwurenvejgwftqbwqvsxkxibmzxfonvjltxhvzxfbzdbbebbqhaimwwmiahqbbebbdzbfxzvhxtljvnofxzmbixkxsvqwbqtfwgjevnexzntmcmyijcfsggyawauruborffuupsaqpbjaefdgoplveawkuukwaevlpogdfeajbpqaspuuffroburuawayggsfcjiymcmtnzxtahufjzmosibmaedbbbmxywugregruwnxbggmedefzyxkqwizfdqqdfziwqkxyzfedemggbxnwurgerguwyxmbbbdeambisomzjfuhxnofelfbmckmwxauekjkzzqhqpwanltchnzkukemnzgyvhkblkmppmklbkhvygznmekukznhctlnawpqhqzzkjkeuaxwmkcmbflefoetzronzkqadxhpnovmvcocdhqlfzlxcjctiegixfwyanjypkrfpetfmuxoaljfwouvohnws"
        , 100))
