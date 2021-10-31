package Easy.ArrayTest;

import java.util.Arrays;

public class search {

    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return 0;
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) return 0;
        int count = 1, l = index - 1, r = index + 1;
        while (l >= 0) if (nums[l--] == target) count++;
        while (r < len) if (nums[r++] == target) count++;
        return count;
    }


    public int search2(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        return index < 0 ? -1 : index;
    }

    public static void main(String[] args) {

    }
}
