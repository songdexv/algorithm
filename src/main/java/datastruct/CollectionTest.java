package datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author songdexu
 * @date 2019/5/14
 */
public class CollectionTest {
    /**
     * 有序列表交集
     *
     * @param a
     * @param b
     *
     * @return
     */
    public static List<Integer> intersection(List<Integer> a, List<Integer> b) {
        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) {
                i++;
            } else if (a.get(i) > b.get(j)) {
                j++;
            } else {
                list.add(a.get(i));
                i++;
                j++;
            }
        }
        return list;
    }

    /**
     * 有序列表并集
     *
     * @param a
     * @param b
     *
     * @return
     */
    public static List<Integer> union(List<Integer> a, List<Integer> b) {
        List<Integer> list = new ArrayList<Integer>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) {
                list.add(a.get(i));
                i++;
            } else if (a.get(i) > b.get(j)) {
                list.add(b.get(j));
                j++;
            } else {
                list.add(a.get(i));
                i++;
                j++;
            }
        }
        if (i < a.size()) {
            for (; i < a.size(); i++) {
                list.add(a.get(i));
            }
        }
        if (j < b.size()) {
            for (; j < b.size(); j++) {
                list.add(b.get(j));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 3, 5, 6, 7, 8, 9);
        List<Integer> b = Arrays.asList(3, 4, 5, 7, 9, 10, 11, 12, 13);
        List<Integer> intersection = intersection(a, b);
        for (int e : intersection) {
            System.out.print(e + " ");
        }
        System.out.println("-----------");
        List<Integer> union = union(a,b);
        for (int e : union) {
            System.out.print(e + " ");
        }
    }
}
