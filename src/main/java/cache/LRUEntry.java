package cache;

/**
 * Created by songdexv on 2018/3/5.
 */
public class LRUEntry<K, V extends ValueHolder> {
    private K key;
    private V valueHolder;
    private LRUEntry<K, V> pre;
    private LRUEntry<K, V> next;

    public LRUEntry(K key, V valueHolder) {
        this.key = key;
        this.valueHolder = valueHolder;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValueHolder() {
        return valueHolder;
    }

    public void setValueHolder(V valueHolder) {
        this.valueHolder = valueHolder;
    }

    public LRUEntry<K, V> getPre() {
        return pre;
    }

    public void setPre(LRUEntry<K, V> pre) {
        this.pre = pre;
    }

    public LRUEntry<K, V> getNext() {
        return next;
    }

    public void setNext(LRUEntry<K, V> next) {
        this.next = next;
    }
}
