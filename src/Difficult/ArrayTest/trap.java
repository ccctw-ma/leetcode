package Difficult.ArrayTest;


/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/4  */

public class trap {


    public int trap(int[] height) {
        if(height.length<=2) return 0;
        int left=0,right=2,sum=0,lmax=height[left],rmax=height[right];
        int i=1;
        while (i<height.length-1){
            int r=i+1;
            while (r<height.length){
                if(height[r]>=rmax){
                    rmax=height[r];
                    right=r;
                }
                r++;
            }
            for (;i<right;i++){
                lmax=(height[i]>lmax?height[i]:lmax);
                int max=Math.min(lmax,rmax);
                if(height[i]<max) sum+=max-height[i];
            }
            lmax=Math.max(lmax,rmax);
            rmax=0;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new trap().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
