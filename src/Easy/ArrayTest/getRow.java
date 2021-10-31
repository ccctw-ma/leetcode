package Easy.ArrayTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * 
 * */

/**
 * @author 马世臣 
 * @// TODO: 2020/1/28 119. 杨辉三角 II */

public class getRow {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        if(rowIndex<1) return list;
        List<Integer> temp = new ArrayList<>();
        for (int i=0;i<rowIndex;i++){
            temp.clear();
            temp.addAll(list);
            list.clear();
            list.add(1);
            for (int j=0;j<i;j++){
                list.add(temp.get(j)+temp.get(j+1));
            }
            list.add(1);
        }
        return list;
    }

    /**
     * 获取杨辉三角的指定行
     * 直接使用组合公式C(n,i) = n!/(i!*(n-i)!)
     * 则第(i+1)项是第i项的倍数=(n-i)/(i+1);
     * 可以直接用数学公式进行推导
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        long cur = 1;
        for (int i = 0; i <= rowIndex; i++) {
            res.add((int) cur);
            cur = cur * (rowIndex-i)/(i+1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new getRow().getRow(1));
    }
}
