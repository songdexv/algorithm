package sort;

/**
 * Created by songdexv on 2018/3/16.
 */
public class BinarySearch {
    public static int binarySearch(int[] array, int item) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == item) {
                return mid;
            } else if (array[mid] > item) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(array, 1));
    }
}
