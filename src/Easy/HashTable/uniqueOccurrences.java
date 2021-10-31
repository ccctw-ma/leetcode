package Easy.HashTable;

import java.util.HashSet;
import java.util.Set;

public class uniqueOccurrences {


    public boolean uniqueOccurrences(int[] arr) {
        int[] bucket=new int[2001];
        for (int i:arr){
            bucket[i+1000]++;
        }
        Set<Integer> set=new HashSet<>();
        for (int i:bucket){
            if(i!=0){
                if(set.contains(i)){
                    return false;
                }else {
                    set.add(i);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
