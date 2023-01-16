package Match.match234;

import java.util.*;

public class match234 {

    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        int len = word.length();
        int index = 0;
        while (index<len){
            while (index<len&&!Character.isDigit(word.charAt(index))) index++;
            int t = index;
            while (t<len&&Character.isDigit(word.charAt(t))) t++;
            if(index!=t){
                String s = word.substring(index,t);
                s = process(s);
                set.add(s);
                index = t;
            }

        }
        return set.size();
    }

    public int reinitializePermutation(int n) {
        if(n==2) return 1;
        int count = 0;
        int[] perm = new int[n];
        for (int i=0;i<n;i++){
            perm[i] = i;
        }
        while (true){
            count++;
            int[] arr = new int[n];
            for (int i=0;i<n;i++){
                if(i%2==0) arr[i] = perm[i/2];
                if(i%2==1) arr[i] = perm[n/2+(i-1)/2];
            }
            if(check(arr)){
                break;
            }else {
                perm = arr;
            }

        }
        return count;
    }

    private boolean check(int[] arr){
        for (int i=0;i<arr.length;i++){
            if(arr[i]!=i) return false;
        }
        return true;
    }


    private String process(String s){
        if(s.length()==1||s.charAt(0)!='0') return s;
        int index = 0;
        while (index<s.length()-1&&s.charAt(index)=='0') index++;
        return s.substring(index);
    }


    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String> map = new HashMap<>();
        for (List<String> list:knowledge){
            String a = list.get(0);
            String b = list.get(1);
            map.put(a,b);
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        int index = 0;
        while (index<s.length()){
            if(chars[index]!='('){
                builder.append(chars[index++]);
            }else {
                int t = index+1;
                while (t<s.length()&&chars[t]!=')') t++;
                String ss = s.substring(index+1,t);
                String re = map.getOrDefault(ss,"?");
                builder.append(re);
                index = t+1;
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(Integer.reverse(10));
    }
}
