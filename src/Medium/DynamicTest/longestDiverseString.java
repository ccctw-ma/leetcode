package Medium.DynamicTest;


/**
 * 1405. 最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 *
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 *
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 *
 * 提示：
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/16  */

public class longestDiverseString {

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder builder=new StringBuilder();
        int[] arr=new int[]{a,b,c};
        char ch=findMax(arr);
        if(arr[ch-'a']>=2){
            builder.append(ch).append(ch);
            arr[ch-'a']-=2;
        }else if(arr[ch-'a']==1){
            builder.append(ch);
            arr[ch-'a']--;
        }
        char pre=ch;
        boolean flag=true;
        while (flag){
            ch=findMax(arr);
            if(arr[ch-'a']==0){
                flag=false;
            }else if(ch==pre){
                if(arr[(ch-'a'+1)%3]>0){
                    arr[(ch-'a'+1)%3]--;
                    char temp= (char) ('a'+(ch-'a'+1)%3);
                    builder.append(temp);
                    pre=temp;
                }else if(arr[(ch-'a'+2)%3]>0){
                    arr[(ch-'a'+2)%3]--;
                    char temp= (char) ('a'+(ch-'a'+2)%3);
                    builder.append(temp);
                    pre=temp;
                }else {
                    flag=false;
                }
            }else {
                if(arr[ch-'a']>=2){
                    builder.append(ch).append(ch);
                    arr[ch-'a']-=2;
                }else if(arr[ch-'a']==1){
                    builder.append(ch);
                    arr[ch-'a']--;
                }
                pre=ch;
            }
        }
        return builder.toString();
    }

    private char findMax(int[] arr){
        int max=Math.max(arr[0],Math.max(arr[1],arr[2]));
        if(max==arr[0]) return 'a';
        if(max==arr[1]) return 'b';
        return 'c';
    }

    public static void main(String[] args) {
        System.out.println(new longestDiverseString().longestDiverseString(1,1,1));
    }
}
