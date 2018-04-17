package sort;

/**
 * Created by songdexv on 2018/4/16.
 */
public class SelectSort {
    public static void sort(int a[],int n){
        int i,j,min;
        for (i=0;i<n;i++){
            min = i;
            for (j=i+1;j<n;j++){
                if (a[j] < a[min]){
                    min = j;
                }
            }
            if (min != i){
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }
}
