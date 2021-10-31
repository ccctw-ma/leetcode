package Difficult.TraceBackTest;



import java.util.*;


/*
* 51. N 皇后
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。



示例：

输入：4
输出：[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。


提示：

皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。*/

/**
 * @author 马世臣
 * @// TODO: 2020/9/3
 * */


public class solveNQueens {



    private Set<Integer> setY;
    private Deque<int[]> points;
    private List<List<String>> res;
    private int n;
    public List<List<String>> solveNQueens(int n) {
        setY=new HashSet<>();
        points=new ArrayDeque<>();
        res=new ArrayList<>();
        this.n=n;
        if(n==0) {res.add(new ArrayList<>());
            return res;
        }
        trace(0,new ArrayList<>());
        return res;
    }


    private void trace(int index,List<String> temp){
        if(index==n){
            res.add(new ArrayList<>(temp));
            return;
        }
        StringBuilder builder=new StringBuilder();
        builder.append(".".repeat(n));
        for (int i=0;i<n;i++){
            if(setY.contains(i)) continue;
            boolean flag=true;
            for (int[] ints:points){
                int diffx=index-ints[0];
                int diffy=i-ints[1];
                if(Math.abs(diffx)==Math.abs(diffy)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println(index+"  "+i);
                points.push(new int[]{index,i});
                setY.add(i);
                builder.setCharAt(i,'Q');
                temp.add(builder.toString());
                trace(index+1,temp);
                temp.remove(temp.size()-1);
                builder.setCharAt(i,'.');
                setY.remove(i);
                points.pop();
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new solveNQueens().solveNQueens(4));
    }
}
