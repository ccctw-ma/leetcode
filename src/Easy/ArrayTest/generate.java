package Easy.ArrayTest;

import java.util.ArrayList;
import java.util.List;



/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/28 118. 杨辉三角 */

public class generate {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listList=new ArrayList<>();
        if(numRows==0)  return listList;
        List<Integer> list=new ArrayList<>();
        list.add(1);
        listList.add(list);
        for (int i=1;i<numRows;i++){
             List<Integer> temp=new ArrayList<>();
             List<Integer> top=listList.get(i-1);
             temp.add(1);
             for (int j=0;j<i-1;j++){
                 temp.add(top.get(j)+top.get(j+1));
             }
             temp.add(1);
             listList.add(temp);
        }
        return listList;
    }

    public static void main(String[] args) {
        
    }
}
