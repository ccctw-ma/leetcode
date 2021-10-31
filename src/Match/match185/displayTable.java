package Match.match185;

import java.util.*;

public class displayTable {


    public List<List<String>> displayTable(List<List<String>> orders) {
        List<String> head=new ArrayList<>();
        Set<String> food=new HashSet<>();
        Set<Integer> numbers=new HashSet<>();
        Map<String,Map<String,Integer>> map=new HashMap<>();
        head.add("Table");
        for (List<String> lists:orders){
            String name=lists.get(2);
            String num=lists.get(1);
            food.add(name);
            numbers.add(Integer.parseInt(num));
            if(!map.containsKey(num)){
                Map<String,Integer> temp=new HashMap<>();
                temp.put(name,1);
                map.put(num,temp);
            }else {
                Map<String,Integer> temp=map.get(num);
                if(temp.containsKey(name)){
                    temp.put(name,temp.get(name)+1);
                }else {
                    temp.put(name,1);
                }
            }
        }
        String[] foods = new String[food.size()];
        int i=0;
        for (String s:food){
            foods[i++]=s;
        }
        Arrays.sort(foods);
        head.addAll(Arrays.asList(foods));
        int[] numbe = new int[numbers.size()];
        i=0;
        for (Integer s:numbers){
            numbe[i++]=s;
        }
        Arrays.sort(numbe);
        List<List<String>> res=new ArrayList<>();
        res.add(head);
        for (int s:numbe){
            List<String> strings=new ArrayList<>();
            strings.add(s+"");
            Map<String,Integer> temp=map.get(s+"");
            for (String s1:foods){
                if(temp.containsKey(s1)){
                    strings.add(temp.get(s1)+"");
                }else {
                    strings.add("0");
                }
            }
            res.add(strings);
        }
        return res;
    }


    public List<List<String>> displayTable2(List<List<String>> orders) {
        List<String> foodNames = new ArrayList<>();
        for(List<String> order : orders){
            String food = order.get(2);
            foodNames.add(food);
        }
        foodNames.sort(Comparator.naturalOrder());

        Comparator.naturalOrder();
        Map<String, Integer> colId = new TreeMap<>();
        for(String food : foodNames){
            if(!colId.containsKey(food)){
                colId.put(food, colId.size());
            }
        }


        TreeMap<Integer, int[]> rowId = new TreeMap<>();
        for(List<String> order : orders){
            Integer table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            if(!rowId.containsKey(table)){
                rowId.put(table, new int[colId.size()]);
            }
            rowId.get(table)[colId.get(food)]++;
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("Table");
        head.addAll(colId.keySet());
        ans.add(head);
        for(Map.Entry<Integer, int[]> entry : rowId.entrySet()){
            int[] row = entry.getValue();
            List<String> vals = new ArrayList<>();
            vals.add(entry.getKey().toString());
            for(int cell : row){
                vals.add(Integer.toString(cell));
            }
            ans.add(vals);
        }
        return ans;
    }


    public static void main(String[] args) {

    }
}
