package Match.match189;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class peopleIndexes {

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
//        TreeMap<List<String>,Integer> treeMap=new TreeMap<>(new Comparator<List<String>>() {
//            @Override
//            public int compare(List<String> o1, List<String> o2) {
//                return o1.size()-o2.size();
//            }
//        });
//        List<Integer> res=new ArrayList<>();
//        for (int i=0;i<favoriteCompanies.size();i++){
//            treeMap.put(favoriteCompanies.get(i),i);
//        }

        List<int[]> list=new ArrayList<>();
        for (int i=0;i<favoriteCompanies.size();i++){
            Collections.sort(favoriteCompanies.get(i));
            list.add(new int[]{favoriteCompanies.get(i).size(),i});
        }
        list.sort((o1, o2) -> o2[0] - o1[0]);
        int[] visted=new int[favoriteCompanies.size()];
        for (int i=0;i<favoriteCompanies.size();i++){
            int index=list.get(i)[1];
            if(visted[index]==0){
                for (int j=i+1;j<favoriteCompanies.size();j++){
                    int dex=list.get(j)[1];
                    if(visted[dex]==0){
                        List<String> a=favoriteCompanies.get(index);
                        List<String> b=favoriteCompanies.get(dex);
                        if(a.size()==b.size()&&contains(a,b)){
                            visted[index]=1;
                            visted[dex]=1;
                        }else if(contains(a,b)){
                            visted[dex]=1;
                        }
                    }
                }
            }
        }
        List<Integer> res=new ArrayList<>();
        for (int i=0;i<visted.length;i++){
            if(visted[i]==0) res.add(i);
        }
        return res;
    }

    private boolean contains(List<String> a,List<String> b){
        if(a.size()<b.size()) return false;
//        for (String s:b){
//            if(!a.contains(s)){
//                return false;
//            }
//        }
//        return true;

        int left=0,right=0;
        while (right<b.size()){
            while (left<a.size()&&!a.get(left).equals(b.get(right))) left++;
            if(left==a.size()){
                return false;
            }
            left++;
            right++;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
