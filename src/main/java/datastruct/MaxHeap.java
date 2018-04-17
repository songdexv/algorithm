package datastruct;

/**
 * 大顶二叉堆
 * Created by songdexv on 2018/4/16.
 */
public class MaxHeap {
    private static int[] heap = new int[30];// 数据
    private static int capcacity = heap.length;// 总的容量
    private static int size = 0;// 实际容量(初始化为0)

    private static int getIndex(int data) {
        for (int i = 0; i < size; i++) {
            if (data == heap[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean insert(int data) {
        if (size == capcacity) {
            return false;
        }
        heap[size] = data;
        filterUp(size);
        size++;
        return true;
    }

    public static boolean remove(int data) {
        if (size == 0) {
            return false;
        }
        int index = getIndex(data);
        if (index == -1) {
            return false;
        }
        heap[index] = heap[--size];
        filterDown(index, size - 1);
        return true;
    }

    /**
     * 最大堆的向上调整算法(从start开始向上直到0，调整堆)
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *
     * @param start 被上调节点的起始位置(一般为数组中最后一个元素的索引)
     */
    private static void filterUp(int start) {
        int c = start;// 当前节点(current)的位置
        int p = (c - 1) / 2;//父(parent)结点的位置
        int tmp = heap[c];
        while (c > 0) {
            if (heap[p] >= tmp) {
                break;
            } else {
                heap[c] = heap[p];
                c = p;
                p = (p - 1) / 2;
            }
        }
        heap[c] = tmp;
    }

    /**
     * 最大堆的向下调整算法
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *
     * @param start 被下调节点的起始位置(一般为0，表示从第1个开始)
     * @param end   截至范围(一般为数组中最后一个元素的索引)
     */
    private static void filterDown(int start, int end) {
        int c = start;// 当前(current)节点的位置
        int l = c * 2 + 1;// 左(left)孩子的位置
        int tmp = heap[c];
        while (l <= end) {
            // "l"是左孩子，"l+1"是右孩子
            if (l < end && heap[l] < heap[l + 1]) {
                l++;
            }
            if (tmp > heap[l]) {
                break;
            } else {
                heap[c] = heap[l];
                c = l;
                l = l * 2 + 1;
            }
        }
        heap[c] = tmp;
    }

    public static void print() {
        int i;
        for (i = 0; i < size; i++) {
            System.out.printf("%d ", heap[i]);
        }
    }

    public static void main(String[] args) {
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        for (int i=0;i<a.length;i++){
            insert(a[i]);
        }
        print();
    }
}
