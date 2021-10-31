package Medium.ArrayTest;


/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/18
 * */

public class maxArea {


    public int maxArea(int[] height) {
        int b=height.length-1;
        int max=0;
        int maxLeft,maxRight=0;
        for (int j=b;j>=0;j--){
            if(height[j]>maxRight){
                maxRight=height[j];
                maxLeft=0;
                for (int i=0;i<j;i++){
                    if(height[i]>maxLeft){
                        maxLeft=height[i];
                        max=Math.max(max,Math.min(maxLeft,maxRight)*(j-i));
                    }
                }
            }
        }
        return max;
    }



    //双指针,一个往右一个往左，每次判断只找距离虽然缩短但是最小值增大的状态
    public int maxArea2(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int min = height[l] < height[r] ? height[l]:height[r];
            max = Math.max(max, min * (r-l));
            while (l<r && height[l] <= min) l++;
            while (l< r && height[r] <= min) r--;

        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new maxArea().maxArea(new int[]{1,8,6,2,5,4,8,3,7,2,3,1,2,3,4,5,6,7,10,2}));
    }
}
