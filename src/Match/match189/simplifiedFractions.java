package Match.match189;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class simplifiedFractions {


    public List<String> simplifiedFractions(int n) {
        List<String> list=new ArrayList<>();
        if(n==1) return list;
        Set<Double> set=new HashSet<>();
        for (int i=1;i<n;i++){
            for (int j=i+1;j<=n;j++){
                double temp=i*1.0/j;
                if(!set.contains(temp)){
                    String s= i + "/" + j;
                    list.add(s);
                    set.add(temp);
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        System.out.println(new simplifiedFractions().simplifiedFractions(4).toString());
    }
}
