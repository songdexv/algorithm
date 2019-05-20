package datastruct;

/**
 * @author songdexu
 * @date 2019/5/14
 */
public class CustomList<T> {
    public ListNode tail = null;
    public ListNode head = null;

    /**
     * 从队列头部加入元素
     *
     * @param value
     */
    public void addNode(T value) {
        ListNode node = new ListNode(value);

        if (tail == null) {
            head = tail = node;
        }

        head.prev = node;
        node.next = head;
        head = node;
    }

    /**
     * 获取倒数第k个节点
     *
     * @param k
     *
     * @return
     */
    public ListNode getFromEnd(int k) {
        int i = 0;
        ListNode cur = tail;
        while (i < k-1) {
            if (cur == null) {
                throw new ArrayIndexOutOfBoundsException(k);
            }
            cur = cur.prev;
            i++;
        }
        return cur;
    }

    public ListNode getFromBegin(int k) {
        int i = 0;
        ListNode cur = head;
        while (i < k - 1) {
            if (cur == null) {
                throw new ArrayIndexOutOfBoundsException(k);
            }
            cur = cur.next;
            i++;
        }
        return cur;
    }

    static class ListNode<T> {
        T value;
        ListNode prev;
        ListNode next;

        public ListNode(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);
        list.addNode(6);
        list.addNode(7);

        ListNode<Integer> node = list.getFromEnd(3);
        System.out.println(node.value);

        node = list.getFromBegin(3);
        System.out.println(node.value);
    }
}
