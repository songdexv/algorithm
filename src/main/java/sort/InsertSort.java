package sort;

/**
 * Created by songdexv on 2018/4/16.
 */
public class InsertSort {
    public static void insertSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int v = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > v) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = v;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 5, 4, 9, 7, 2, 0, 4, 5, 2, 3, 8, 9, 6};
        insertSort(array, array.length);
        for (int e : array) {
            System.out.print(e + " ");
        }
    }
}
