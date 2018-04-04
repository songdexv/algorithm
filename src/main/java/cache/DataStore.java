package cache;

/**
 * Created by songdexv on 2018/3/5.
 */
public interface DataStore<K,V> {
    ValueHolder<V> get(K key);

    boolean put(K key, V value);

    ValueHolder<V> remove(K key);

    void clear();
}
