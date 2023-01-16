package Match.match201;

public class match201_D {


    public boolean canConvertString(String s, String t, int k) {
        if(s.length()!=t.length()) return false;
        int l=s.length();
        int[] dp=new int[26];
        for(int i=0;i<l;i++){
            char a=s.charAt(i);
            char b=t.charAt(i);
            int dif=(b-a+26)%26;
            if(dp[dif]==0){
                dp[dif]=dif;
            }else {
                dp[dif]+=26;
            }
            if(dp[dif]>k) return false;
        }
        return true;
    }

    //贪心算法
    public int minInsertions(String s) {
        // L表示剩余没匹配的'('的数量，R表示剩余没匹配的')'的数量
        int L = 0, R = 0;

        int len = s.length();
        int res = 0;

        for (int i = 0; i < len; i++) {
            // 下一个是'('
            if (s.charAt(i) == '(' ) {
                L++;
            }
            // 下一个是')'
            else {
                // 处理L，如果L==0那么就要加'('来匹配这个右括号
                if (L == 0) {
                    L++;
                    res++;
                }

                // R==1的情况，刚好能匹配
                if (R == 1) {
                    L--;
                    R--;
                }
                // R==0的情况
                else {
                    // R==0 且下一个字符是')',只需要R++
                    if (i < len - 1 && s.charAt(i + 1) == ')' ) R++;

                        // R==0 且下一个字符不是')',需要补一个')'完成匹配
                    else {
                        if (L == 0) res++;
                        else L--;
                        res++;
                        R = 0;
                    }
                }
            }
        }
        // 处理多余的左右括号
        res += Math.abs(2 * L - R);
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new match201_D().canConvertString("abc","bcd",10));
    }
}
