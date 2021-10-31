package BUPT.FormalTest;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class C {


    public static boolean check(char[] chars,int l,int r){
        if(chars[l+2]!='-') return false;
        if(chars[l+5]!='-') return false;
        int count =0;
        for (int i=l;i<=r;i++){
            if(chars[i]=='-') count++;
        }
        return count==2;
    }

    public static void main(String[] args) {

        //显示简单的字符串处理操作，之后判断是否满足时间的格式，在之后后HashMap存一下出现的次数就可以了
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        String s = in.next().trim();
        int[] days = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
//        System.out.println(Arrays.stream(days).sum());
        char[] chars = s.toCharArray();
        int maxd = 1, maxm = 1, maxy = 2013;
        int max = 0;
        Map<String,Integer> map = new HashMap<>();
        String res = null;
        for (int i=0;i<=chars.length-10;i++){
            if(!check(chars,i,i+9)) continue;
            int day = (chars[i]-'0')*10+chars[i+1]-'0';
            int month = (chars[i+3]-'0')*10+chars[i+4]-'0';
            int year = (chars[i+6]-'0')*1000+(chars[i+7]-'0')*100+(chars[i+8]-'0')*10+chars[i+9]-'0';
            if(year>=2013&&year<=2015&&month>=1&&month<=12){
                int d = days[month-1];
                if(day>=1&&day<=d){
                    String a = day<10? "0"+day : String.valueOf(day);
                    String b = month<10? "0"+month : String.valueOf(month);
                    String c = String.valueOf(year);
                    String ss = a+"-"+b+"-"+c;
                    map.put(ss,map.getOrDefault(ss,0)+1);
                    if(map.get(ss)>max){
                        max = map.get(ss);
                        res = ss;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
