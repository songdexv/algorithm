package sort;

/**
 * Created by songdexv on 2018/3/12.
 */
public class QuickSort {
    /**
     * 一次快速排序
     *
     * @param array
     * @param low
     * @param high
     *
     * @return key的下标index，也就是分片的间隔点
     */
    public static int partition(int[] array, int low, int high) {
        //基准点
        int pivot = array[low];
        while (low < high) {
            while (high > low && array[high] >= pivot) {
                high--;
            }
            array[low] = array[high];
            while (high > low && array[low] <= pivot) {
                low++;
            }
            array[high] = array[low];
        }
        array[high] = pivot;
        return high;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low >= high){
            return;
        }
        //进行第一轮排序获取分割点
        int index = partition(array, low, high);
        //排序前半部分
        quickSort(array, low, index - 1);
        //排序后半部分
        quickSort(array, index + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 3, 12, 7, 8, 3, 4, 65, 22};
        quickSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + ",");
        }
    }
}
