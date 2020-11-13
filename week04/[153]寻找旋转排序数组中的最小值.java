//假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。 
//
// 请找出其中最小的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 中的所有整数都是 唯一 的 
// nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转 
// 
// Related Topics 数组 二分查找 
// 👍 293 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMin(int[] nums) {
        // 未旋转
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                // mid 比左侧小，mid 就是旋转点
                return nums[mid];
            }
            if (nums[mid] > nums[mid + 1]) {
                // mid 比右侧大，右侧就是旋转点
                return nums[mid + 1];
            }

            if (nums[left] < nums[mid]) {
                // left - mid 递增，旋转点在右侧
                left = mid + 1;
            } else {
                // 旋转点在左侧
                right = mid - 1;
            }
        }

        return  -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
