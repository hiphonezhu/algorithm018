## 1. 位运算常用
- x%2 == 1  等价于  x&1 == 1  
- x%2 == 0  等价于  x&1 == 0
- x / 2  等价于  x >> 1
- x&(x-1): 清零最低位的1 ---> 统计二进制1的个数、一个数是否为2的N次幂
- x&-x: 得到最低位的1所表示的值
- x&~x: 0



## 2. 布隆过滤器
- 常用于判断一个元素是否存在一个集合中。数据量很大的时候，使用常规的数据结构空间复杂度非常高，所以产生了布隆过滤器。
  
- 布隆过滤器由一个很长的二进制数组和一系列的哈希函数组成，空间效率和查询效率高，有一定的误判率

- 布隆过滤器添加元素
  - 将要添加的元素给k个哈希函数
  - 得到对应于位数组上的k个位置
  - 将这k个位置设为1

- 布隆过滤器查询元素
  - 将要查询的元素给k个哈希函数
  - 得到对应于位数组上的k个位置
  - 如果k个位置有一个为0，则肯定不在集合中
  - 如果k个位置全部为1，则可能在集合中

- 应用场景：垃圾邮件/短信、缓存穿透、网页爬虫



## 3. 常用排序
- 选择排序 O(n^2)：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。 
    ```java
    for (int i = 0; i < nums.length - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] < nums[minIndex]) {
                minIndex = j;
            }
        }
        int temp = nums[i];
        nums[i] = nums[minIndex];
        nums[minIndex] = temp;
    }
    ```

- 插入排序 O(n^2)：构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
    ```java
    for (int i = 1; i < nums.length; i++) {
        int preIndex = i - 1;
        int current = nums[i];
        while (preIndex >= 0 && nums[preIndex] > current) {
            nums[preIndex + 1] = nums[preIndex];
            preIndex--;
        }
        nums[preIndex + 1] = current;
    }
    ```

- 冒泡排序 O(n^2)：比较相邻两个元素，较大的放后面，一趟下来最大的元素一定在最后面
    ```java
    for (int i = 0; i < nums.length; i++) {
        for (int j = 0; j < nums.length - 1 - i; j++) {
            if (nums[j] > nums[j + 1]) {
                int temp = nums[j + 1];
                nums[j + 1] = nums[j];
                nums[j] = temp;
            }
        }
    }
    ```

- 快速排序 O(nlogn)：选取一个数字，把比它小的放到左边，比它大的放到右边
    ```java
    private void quickSort(int[] nums, int begin, int end) {
        if (begin >= end) return;
        int mid = partition(nums, begin, end);
        quickSort(nums, begin, mid - 1);
        quickSort(nums, mid + 1, end);
    }
    
    private int partition(int[] nums, int begin, int end) {
        int pivot = end;
        int mid = begin;
        for (int i = begin; i < end; i++) {
            if (nums[i] < nums[pivot]) {
                int temp = nums[i];
                nums[i] = nums[mid];
                nums[mid] = temp;
                mid++;
            }
        }
        int temp = nums[mid];
        nums[mid] = nums[pivot];
        nums[pivot] = temp;
        return mid;
    }
    ```

- 归并排序 O(nlogn)：将数组一分为二，排序左右数组，再将两个排序数组合并
    ```java
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, mid, left, right);
    }

    /**
     * 合并两个有序数组
     * 数组1：[left, mid]
     * 数组2：[mid + 1, right]
     *
     * @param nums
     * @param mid
     * @param left
     * @param right
     */
    private void merge(int[] nums, int mid, int left, int right) {
        int[] temp = new int[right - left + 1];
        int leftStartIndex = left;
        int rightStartIndex = mid + 1;
        int tempIndex = 0;
        while (leftStartIndex <= mid && rightStartIndex <= right) {
            temp[tempIndex++] = nums[leftStartIndex] < nums[rightStartIndex] ? nums[leftStartIndex++] : nums[rightStartIndex++];
        }
        while (leftStartIndex <= mid) {
            temp[tempIndex++] = nums[leftStartIndex++];
        }
        while (rightStartIndex <= right) {
            temp[tempIndex++] = nums[rightStartIndex++];
        }

        for (int i = 0; i < temp.length; i++) {
            nums[left + i] = temp[i];
        }
    }
    ```

- 堆排序 O(nlogn)：小顶堆、大顶堆实现
    ```java
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++) {
        priorityQueue.add(nums[i]);
    }
    for (int i = 0; i < nums.length; i++) {
        nums[i] = priorityQueue.poll();
    }
    ```
  
- 计数排序（正整数）：统计每个数字 i 出现的次数，存在下标为 i 的数组中，然后遍历该数组，生成有序数组
    ```java
    int maxValue = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > maxValue) {
            maxValue = nums[i];
        }
    }
    // 统计每个数字 i 出现的次数，存在下标为 i 的数组中
    int[] array = new int[maxValue + 1];
    for (int i = 0; i < nums.length; i++) {
        array[nums[i]]++;
    }
    // 生成有序数组
    int ansIndex = 0;
    for (int i = 0; i < array.length; i++) {
        while (array[i]-- > 0) {
            // 数字 i 出现的次数
            nums[ansIndex++] = i;
        }
    }
    ```

- 桶排序（正整数）：计数排序需要的数组空间为待排序数组的最大值，而桶排序通过一个函数将数字分配到不同的桶中，每个桶分别排序，最后合并各个桶
    ```java
    // 每个桶用堆排序
    PriorityQueue[] bucket = new PriorityQueue[10];
    for (int i = 0; i < nums.length; i++) {
        int bucketIndex = Math.abs(new Integer(nums[i]).hashCode()) % bucket.length;
        if (bucket[bucketIndex] == null) {
            bucket[bucketIndex] = new PriorityQueue<Integer>();
        }
        PriorityQueue<Integer> queue = bucket[bucketIndex];
        queue.offer(nums[i]);
    }
    // 将不为空的桶按顺序合并
    int ansIndex = 0;
    for (PriorityQueue<Integer> queue : bucket) {
        while (queue != null && !queue.isEmpty()) {
            nums[ansIndex++] = queue.poll();
        }
    }
    ```

- 基数排序（正整数）：从最低位开始排序，再排次低位