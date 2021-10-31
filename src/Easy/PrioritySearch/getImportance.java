package Easy.PrioritySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getImportance {

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Employee> map=new HashMap<>();
        for (Employee employee:employees){
            map.put(employee.id,employee);
        }
        Employee boss=map.get(id);
        int sum=boss.importance;
        List<Integer> employeeList=boss.subordinates;
        while (!employeeList.isEmpty()){
            int count=employeeList.size();
            List<Integer> temp=new ArrayList<>();
            for (int i=0;i<count;i++){
                Employee employee=map.get(employeeList.get(i));
                sum+=employee.importance;
                temp.addAll(employee.subordinates);
            }
            employeeList.clear();
            employeeList.addAll(temp);
        }
        return sum;
    }

    class Solution {
        Map<Integer, Employee> emap;
        public int getImportance(List<Employee> employees, int queryid) {
            emap = new HashMap();
            for (Employee e: employees) emap.put(e.id, e);
            return dfs(queryid);
        }
        public int dfs(int eid) {
            Employee employee = emap.get(eid);
            int ans = employee.importance;
            for (Integer subid: employee.subordinates)
                ans += dfs(subid);
            return ans;
        }
    }


    public static void main(String[] args) {

    }
}
