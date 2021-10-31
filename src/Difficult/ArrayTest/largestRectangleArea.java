package Difficult.ArrayTest;


/*
* 84. 柱状图中最大的矩形
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。





以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。





图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。



示例:

输入: [2,1,5,6,2,3]
输出: 10*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马世臣
 * @// TODO: 2020/5/30
 * */

public class largestRectangleArea {


    public int largestRectangleArea(int[] heights) {
        if(heights.length==0) return 0;
        int maxArea=heights[0],minlength=heights[0],index=0;
        for (int i=1;i<heights.length;i++){
            int h=heights[i];
            if(h<=minlength){
                index=i;
                minlength=h;
            }
            for (int j=i;j>index;j--){
                h=Math.min(heights[j],h);
                maxArea=Math.max(maxArea,h*(i-j+1));
            }
            if(minlength!=0){
                maxArea=Math.max(maxArea,minlength*(i+1));
            }
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights){
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] new_heights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) new_heights[i] = heights[i - 1];
        //System.out.println(Arrays.toString(new_heights));
        for (int i = 0; i < new_heights.length; i++) {
            //System.out.println(stack.toString());
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new largestRectangleArea().largestRectangleArea2(new int[]{2,1,5,6,2,3}));
    }
}
