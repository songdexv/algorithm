package sort;

/**
 * Created by songdexv on 2018/4/17.
 */
public class HeapSort {
    /**
     * 堆排序(从小到大)
     *
     * @param a 待排序的数组
     * @param n 数组的长度
     */
    public static void heapSortAsc(int[] a, int n) {
        int i, tmp;
        // 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
        for (i = n / 2 - 1; i >= 0; i--) {
            maxHeapDown(a, i, n - 1);
        }
        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = n - 1; i > 0; i--) {
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
            // 即，保证a[i-1]是a[0...i-1]中的最大值。
            maxHeapDown(a, 0, i - 1);
        }
    }

    private static void maxHeapDown(int[] a, int start, int end) {
        int c = start;
        int l = c * 2 + 1;
        int tmp = a[c];
        while (l <= end) {
            if (a[l] < a[l + 1]) {
                l++;
            }
            if (tmp > a[l]) {
                break;
            } else {
                a[c] = a[l];
                c = l;
                l = c * 2 + 1;
            }
        }
        a[c] = tmp;
    }

    private static void minHeapDown(int[] a, int start, int end) {
        int c = start;
        int l = c * 2 + 1;
        int tmp = a[c];
        while (l <= end) {
            if (l < end && a[l] > a[l + 1]) {
                l++;
            }
            if (tmp <= a[l]) {
                break;
            } else {
                a[c] = a[l];
                c = l;
                l = c * 2 + 1;
            }
        }
        a[c] = tmp;
    }

    /**
     * 堆排序(从大到小)
     *
     * @param a
     * @param n
     */
    public static void heapSortDesc(int[] a, int n) {
        int i, tmp;
        // 从(n/2-1) --> 0逐次遍历每。遍历之后，得到的数组实际上是一个最小堆。
        for (i = n / 2 - 1; i >= 0; i--) {
            minHeapDown(a, i, n - 1);
        }
        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = n - 1; i > 0; i--) {
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            minHeapDown(a, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        int i;
        int a[] = {20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80};
        System.out.printf("before sort:");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
//        heapSortAsc(a, a.length);
        heapSortDesc(a, a.length);
        System.out.printf("after  sort:");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
    }
}
