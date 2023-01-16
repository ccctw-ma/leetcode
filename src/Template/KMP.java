package sort;

public class KMP {

    private int[] get_next(String t){
        int len=t.length();
        int[] next=new int[len+1];
        next[0]=0;
        int i=1,j=0;
        while (i<len){
            if(j==0||t.charAt(i-1)==t.charAt(j-1)){
                i++;
                j++;
                next[i]=j;
            }else {
                j=next[j];
            }
        }
        return next;
    }

    public int Index_KMP(String s,String t){
        int[] next=get_next(t);
        int i=0,j=0;
        while (i<s.length()&&j<t.length()){
            if(j==0||s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else {
                j=next[i];
            }
        }
        if(j>t.length()){
            return i-t.length();
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String s="32+i78";
        int x = Integer.parseInt(s.substring(0,s.indexOf('+')));
        int y = Integer.parseInt(s.substring(s.indexOf('+')+2));
        System.out.println(x+" "+y);
    }
}
