package Match.TianChi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class match20_8_16 {


    public String reformatString(String str, int[] sublen) {
        String[] strings=new String[sublen.length];
        int left=0;
        for (int i=0;i<sublen.length;i++){
            int right=sublen[i];
            strings[i]=str.substring(left,left+right);
            left=left+right;
        }
        for (int i=0;i<strings.length;i+=2){
            if(i+1==strings.length) break;
            String temp=strings[i];
            strings[i]=strings[i+1];
            strings[i+1]=temp;
        }
        StringBuilder builder=new StringBuilder();
        for (String s:strings){
            builder.append(s);
        }
        return  builder.toString();
    }


    public int IfIntersect(double[] position) {
        double x1=position[0];
        double y1=position[1];
        double ra=position[2];
        double x=position[3];
        double y=position[4];
        double rb=position[5];
        double x2=position[6];
        double y2=position[7];
        double diff=2e-9;
        double d;
        System.out.println(diff+1);
        //三条边的长度
        double a=lineSpace(x1,y1,x2,y2);

        double b=lineSpace(x1,y1,x,y);

        double c=lineSpace(x2,y2,x,y);

        //c为最长边，为直角或钝角
        if(a*a+b*b<=c*c+diff){
            d=b;
        }
        //为最长边，为直角或钝角
        else if(a*a+c*c<=b*b+diff){
            d=c;
        }
        //为锐角三角形
        else {
            //有海伦公式求面积再求距离
            double p=(a+b+c)/2;
            double s=Math.sqrt(p*(p-a)*(p-b)*(p-c));
            d=s*2/a;
        }
        return d<=(ra+rb+diff)?1:-1;
    }

    private double lineSpace(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }


    public long doingHomework(int[] cost, int[] val) {
        long[] cos=new long[cost.length];
        cos[0]=cost[0];
        for (int i=1;i<cos.length;i++){
            cos[i]+=cost[i];
            cos[i]+=cos[i-1];
        }
        long sum=0;
        for (int i:val){
            int index=Arrays.binarySearch(cos,i);
            if(index<0){
                index=-index-1;
            }
            if(cos[index]>i){
                index--;
            }
            if(index<0){
                sum+=0;
            }else {
                sum+=cos[index];

            }
        }
        return sum;
    }

    public String kthString(int n, long k) {
        long max= (long) (3*(Math.pow(2,n-1)));
        if(max<k){
            return "";
        }

        char[] chars=new char[]{'d','e','f'};
        StringBuilder builder=new StringBuilder();
        char pre='a';
        k--;
        for (int i=0;i<n;i++){
            long temp= (long) Math.pow(2,n-i-1);
            int index= (int) (k/temp);
            char ch;
            if(pre=='a'){
                ch=chars[index];
            }else {
                List<Character> characters=new ArrayList<>();
                for (char c:chars){
                    if(c!=pre){
                       characters.add(c);
                    }
                }
                characters.sort(Comparator.comparingInt(o -> o));
                ch=characters.get(index);
            }
            builder.append(ch);
            pre=ch;
            k%=temp;
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        System.out.println(new match20_8_16().IfIntersect(new double[]{0,0,2.5,3,2,0.5,0,2}));
    }
}
