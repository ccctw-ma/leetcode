package Match.match185;

public class minNumberOfFrogs {



    public int minNumberOfFrogs(String croakOfFrogs) {
        int len=croakOfFrogs.length();
        if(len%5!=0||croakOfFrogs.charAt(0)!='c'||croakOfFrogs.charAt(len-1)!='k') return -1;
        char[] ch=croakOfFrogs.toCharArray();
        int[] num=new int[127];
        int count=1;
        for(char c:ch){
            num[c]++;
            //如果数组不是非递增的，那么就是无效数据
            if(!(num['c']>=num['r']&&num['r']>=num['o']&&num['o']>=num['a']&&num['a']>=num['k'])) return -1;
            //显然，当字符串为c时，青蛙数量才会增加
            if(c=='c'){
                count=Math.max(count,num['c']-num['k']);
            }
        }
        return count;
    }

    public int minNumberOfFrogs2(String croakOfFrogs){
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for(char x : croakOfFrogs.toCharArray()){
            if(x == 'c') c += 1;
            else if(x == 'r') r += 1;
            else if(x == 'o') o += 1;
            else if(x == 'a') a += 1;
            else if(x == 'k') k += 1;
            else return -1;
            if(r > c || o > r || a > o || k > a) return -1;
            ans = Math.max(c, ans);
            if(k == 1){
                c -= 1;
                r -= 1;
                o -= 1;
                a -= 1;
                k -= 1;
            }
        }
        if(c==0) return -1;
        return ans;
    }

    public static void main(String[] args) {

    }
}
