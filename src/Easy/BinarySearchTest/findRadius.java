package Easy.BinarySearchTest;


/**
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 *
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 *
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 *
 * 说明:
 *
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 示例 1:
 *
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 *
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 **/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/2/8  475. 供暖器*/

public class findRadius {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses); //对两个数组排序
        Arrays.sort(heaters);
        int max = 0;
        int temp = 0;
        //从头开始遍历两个数组，每次只有一个下标+1
        for (int i = 0, j = 0; i < houses.length && j < heaters.length; ) {
            if (houses[i] <= heaters[j]) {
                temp = heaters[j] - houses[i];
                i++;
            } else if (j < heaters.length - 1) {
                temp = Math.min(heaters[j+1] - houses[i], houses[i] - heaters[j]);
                if (houses[i] < heaters[j + 1]) {
                    i++;
                } else {
                    j ++;
                }
            } else if (j == heaters.length - 1) {
                temp = houses[i] - heaters[j];
                i++;
            }
            max = max > temp ? max : temp;
        }
        return max;
    }


    public static void main(String[] args) {

    }
}
