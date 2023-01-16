package Match.macth194;

import java.util.*;

public class match194 {

/*Map<String, Set<Integer>> setMap=new HashMap<>();
        for (int i=0;i<names.length;i++){
            String s=names[i];
            if(strings.containsKey(s)){
                int t=strings.get(s)+1;
                Set<Integer> set=setMap.get(s);
                while (set.contains(t)) t++;
                setMap.get(s).add(t);
                names[i]=s+"("+t+")";
                strings.put(s,t);
                strings.put(names[i],0);
                setMap.put(names[i],new HashSet<>());
            }else {
                int a=s.lastIndexOf('(');
                if(a!=-1) {
                    int x=Integer.parseInt(s.substring(a,s.length()-1));
                    String temp=s.substring(a);
                    if(setMap.containsKey(temp)){
                        setMap.get(temp).add(x);
                    }else {
                        setMap.put(temp,new HashSet<>());
                        setMap.get(temp).add(x);
                    }
                }
                strings.put(s,0);
                setMap.put(s,new HashSet<>());
            }

        }*/

    public String[] getFolderNames(String[] names) {
        Map<String,Integer> map=new HashMap<>();
        for (int i=0;i<names.length;i++){
            String s=names[i];
            if(map.containsKey(s)){
                int index=map.get(s)+1;
                while (true){
                    String temp=s+"("+index+")";
                    if(map.containsKey(temp)){
                        index++;
                    }else {
                        names[i]=temp;
                        map.put(s,index);
                        map.put(temp,0);
                        break;
                    }
                }
            }else {
                map.put(s,0);
            }
        }
        return names;
    }

    public int[] avoidFlood(int[] rains) {
        int[] err = new int[]{};
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain > 0) {
                if (!map.containsKey(rain)) {
                    map.put(rain, i);
                    rains[i] = -1;
                } else {
                    if (count > 0) {
                        int idx = 0;
                        while(idx < stack.size() && stack.get(idx) < map.get(rain)){
                            idx++;
                        }
                        if(idx >= stack.size()){
                            return err;
                        }
                        rains[stack.remove(idx)] = rain;
                        rains[i] = -1;
                        count--;
                        map.put(rain, i);
                    } else {
                        return err;
                    }
                }
            } else {
                if (map.size() > 0) {
                    count++;
                }
                stack.addLast(i);
            }
        }
        while (!stack.isEmpty()) {
            rains[stack.pop()] = 1;
        }
        return rains;
    }


    public int[] avoidFlood2(int[] rains) {
        int n = rains.length;
        Map<Integer, Integer> registries = new HashMap<>(n);
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            next[i] = registries.getOrDefault(rains[i], n);
            registries.put(rains[i], i);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        PriorityQueue<Rain> pq = new PriorityQueue<>(n, Comparator.comparingInt(a -> a.day));
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                if (!pq.isEmpty()) {
                    ans[i] = pq.remove().index;
                }else{
                    ans[i] = 1;
                }
            } else {
                Rain rain = new Rain();
                rain.index = rains[i];
                rain.day = next[i];
                pq.add(rain);
            }
            if (!pq.isEmpty() && pq.peek().day <= i) {
                return new int[0];
            }
        }
        return ans;
    }

    class Rain {
        int index;
        int day;
    }

    public static void main(String[] args) {

    }
}
