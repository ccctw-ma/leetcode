package BUPT.test01;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            String a = in.next();
            String b = in.next();
            if(a.equals(b)){
                System.out.println(0);
            }else {
                //暴力破解
                int lena = a.length();
                int lenb = b.length();
                if(lena>lenb){
                    String temp = a;
                    a = b;
                    b = temp;
                    int t = lena;
                    lena = lenb;
                    lenb = t;
                }
                int min = lena+lenb;
                for (int i=1;i<=lena;i++){
                    for (int j=0;j<=lena-i;j++){
                        String s = a.substring(j,j+i);
                        int index = b.indexOf(s);
                        if(index!=-1){
                            int o1 = lena-i;
                            int o2 = lenb-i;
                            min = Math.min(min,o1+o2);
                        }
                    }
                }
                System.out.println(min);
            }
        }
    }
}
