package cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by songdexv on 2018/3/5.
 */
public class WeakValueDataStore<K,V> implements DataStore<K,V> {
    private ConcurrentHashMap<K,ValueHolder<V>> map = new ConcurrentHashMap<K, ValueHolder<V>>();

    public ValueHolder<V> get(K key) {
        return map.get(key);
    }

    public boolean put(K key, V value) {
        ValueHolder<V> vh = new WeakValueHolder<V>(value);
        map.put(key, vh);
        return true;
    }

    public ValueHolder<V> remove(K key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }
}
