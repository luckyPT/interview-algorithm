package com.pt.leetcode;

import java.util.*;

/**
 * leetCode 15
 *
 * @author panTeng
 */
public class ThreeSum {
    /**
     * 初步优化，时间复杂度n^2 并且需要避免不必要的继续搜索
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> num2lastIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            num2lastIndices.put(nums[i], i);
        }

        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < len - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = 0 - nums[i];
            if (nums[i + 1] > remain) {
                break;
            }
            for (int j = i + 1; j < len - 1; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                remain = 0 - nums[i] - nums[j];
                if (nums[j + 1] > remain) {
                    break;
                }
                if (num2lastIndices.getOrDefault(remain, -1) > j) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    tmp.add(remain);
                    ret.add(tmp);
                }
            }
        }
        return ret;
    }

    /**
     * 暴力匹配 s时间复杂度n^3
     * 逻辑没有问题，可以通过leetCode的311ge测试用例，但时间复杂度略高第 312个超出时间限制
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        int[] tmpArray = threeSort(nums[i], nums[j], nums[k]);
                        tmp.add(tmpArray[0]);
                        tmp.add(tmpArray[1]);
                        tmp.add(tmpArray[2]);
                        if (!ret.contains(tmp)) {
                            ret.add(tmp);
                        }
                    }
                }
            }
        }
        return ret;
    }

    public static int[] threeSort(int i, int j, int k) {
        int[] ret = new int[3];
        if (i <= j && i <= k) {
            ret[0] = i;
            if (j <= k) {
                ret[1] = j;
                ret[2] = k;
            } else {
                ret[1] = k;
                ret[2] = j;
            }
        } else if (j <= i && j <= k) {
            ret[0] = j;
            if (i <= k) {
                ret[1] = i;
                ret[2] = k;
            } else {
                ret[1] = k;
                ret[2] = i;
            }
        } else {
            ret[0] = k;
            if (i <= j) {
                ret[1] = i;
                ret[2] = j;
            } else {
                ret[1] = j;
                ret[2] = i;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4, 2, 3, 4, 51, 21, 43, 13, 0, 1, 3, 4, 35008, 46482, -75289, 65799, 20057, 7170, 41326, -76069, 90840, -81253, -50749, 3649, -42315, 45238, -33924, 62101, 96906, 58884, -7617, -28689, -66578, 62458, 50876, -57553, 6739, 41014, -64040, -34916, 37940, 13048, -97478, -11318, -89440, -31933, -40357, -59737, -76718, -14104, -31774, 28001, 4103, 41702, -25120, -31654, 63085, -3642, 84870, -83896, -76422, -61520, 12900, 88678, 85547, 33132, -88627, 52820, 63915, -27472, 78867, -51439, 33005, -23447, -3271, -39308, 39726, -74260, -31874, -36893, 93656, 910, -98362, 60450, -88048, 99308, 13947, 83996, -90415, -35117, 70858, -55332, -31721, 97528, 82982, -86218, 6822, 25227, 36946, 97077, -4257, -41526, 56795, 89870, 75860, -70802, 21779, 14184, -16511, -89156, -31422, 71470, 69600, -78498, 74079, -19410, 40311, 28501, 26397, -67574, -32518, 68510, 38615, 19355, -6088, -97159, -29255, -92523, 3023, -42536, -88681, 64255, 41206, 44119, 52208, 39522, -52108, 91276, -70514, 83436, 63289, -79741, 9623, 99559, 12642, 85950, 83735, -21156, -67208, 98088, -7341, -27763, -30048, -44099, -14866, -45504, -91704, 19369, 13700, 10481, -49344, -85686, 33994, 19672, 36028, 60842, 66564, -24919, 33950, -93616, -47430, -35391, -28279, 56806, 74690, 39284, -96683, -7642, -75232, 37657, -14531, -86870, -9274, -26173, 98640, 88652, 64257, 46457, 37814, -19370, 9337, -22556, -41525, 39105, -28719, 51611, -93252, 98044, -90996, 21710, -47605, -64259, -32727, 53611};
        ThreeSum sum = new ThreeSum();

        long start = System.currentTimeMillis();
        System.out.println(sum.threeSum2(nums));
        System.out.println(String.format("sum2 耗时 %d ms", System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(sum.threeSum(nums));
        System.out.println(String.format("sum 耗时 %d ms", System.currentTimeMillis() - start));


    }
}
