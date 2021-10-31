package Match.match189;

import java.util.Arrays;
import java.util.Comparator;

public class arrangeWords {

    public String arrangeWords(String text) {
        char ch=text.charAt(0);
        text=Character.toLowerCase(ch)+text.substring(1);
        String[] strings=text.split(" ");
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        StringBuilder builder=new StringBuilder();
        for (String s:strings){
            builder.append(s).append(" ");
        }
        String res=builder.toString().trim();
        char c=res.charAt(0);
        res=Character.toUpperCase(c)+res.substring(1);
        return res;
    }

    public static void main(String[] args) {

    }

}
