package sort;

import java.util.Arrays;

/**
 * Created by songdexv on 2018/4/16.
 */
public class BubbleSort {
    public static void bubbleSort(int[] array, int n) {
        int flag = 0;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = 1;
                }
            }
            if (flag == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {20, 40, 30, 10, 60, 50};
        bubbleSort(a, a.length);
        for (int i=0; i<a.length; i++){
            System.out.printf("%d ", a[i]);
        }
    }
}
