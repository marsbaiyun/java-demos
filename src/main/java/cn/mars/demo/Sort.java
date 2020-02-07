package cn.mars.demo;

/**
 * Description：
 * Created by Mars on 2019/12/25.
 */
public class Sort {


    public static void quickSort(int[] arr, int start, int end) {
        if (end <= start) {
            return;
        }
        int low = start;
        int high = end;
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high]; // 将小于 pivot 的数放在地位
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low]; // 将大于 pivot 的数放在高位
        }
        arr[low] = pivot;
        quickSort(arr, start, low - 1); // 递归排序左半部分
        quickSort(arr, low + 1, end); // 递归排序右半部分
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 小摩有一个N个数的数组，他想将数组从小到大 排好序，
         但是萌萌的小摩只会下面这个操作：
         任取数组中的一个数然后将它放置在数组的最后一个位置。
         问最少操作多少次可以使得数组从小到大有序？
         输入描述:
         首先输入一个正整数N，接下来的一行输入N个整数。
         (N <= 50, 每个数的绝对值小于等于1000)
         输出描述:
         输出一行操作数
         输入例子1:
         4
         19 7 8 25
         输出例子1:
         2
         例子说明1:
         19放到最后，25放到最后，两步完成从小到大排序
     * @param arr
     */
    public static void sortArray(int[] arr) {
        int last = 1001;
        int idx = 0;
        boolean flag = true;
        while (flag) {

        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 5, 4, 8, 6, 3, 9, 0, 1, 7, 2 };
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }
}
