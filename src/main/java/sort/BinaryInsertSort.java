package sort;

/**
 * @author songdexu
 * @date 2019/5/14
 */
public class BinaryInsertSort {
    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (tmp >= a[middle]) {
                    left = middle + 1;
                } else if (tmp < a[middle]) {
                    right = middle - 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            a[left] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 5, 4, 9, 7, 2, 0, 4, 5, 2, 3, 8, 9, 6};
        sort(array);
        for (int a : array) {
            System.out.print(a + " ");
        }

    }
}
