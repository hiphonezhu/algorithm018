1、使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

分析：和【153. 寻找旋转排序数组中的最小值】是一个意思，最小值的索引就是无序的地方，代码如下：

public int findRotateIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // mid 比右边大，右边肯定是旋转点
                return mid + 1;
            }

            if (nums[mid] < nums[mid - 1]) {
                // mid 比左边小，mid 肯定就是旋转点
                return mid;
            }

            if (nums[left] < nums[mid]) {
                // 左边递增，较小数肯定在右边
                left = mid + 1;
            } else {
                // 右侧递增，较小数肯定在左边
                right = mid - 1;
            }
        }

        return -1;
    }



2、什么时候可以用贪心算法
2.1 当前步骤选择一个最优解，能保证全局找到解，但这个解不一定是最优解



3、什么时候可以用二分查找
3.1 单调自增、单调递减
3.2 存在上下界，这样才能往中间夹逼
3.3 可以通过索引访问，这样才方便找到”中间“

