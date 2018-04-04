package cache;

import java.util.HashMap;

/**
 * Created by songdexv on 2018/3/5.
 */
public class LRUDataStore<K, V> implements DataStore<K, V> {
    private final int MAX_CACHE_SIZE;
    private LRUEntry first;
    private LRUEntry last;

    private HashMap<K, LRUEntry<K, ValueHolder<V>>> hashMap;

    public LRUDataStore(int maxSize) {
        this.MAX_CACHE_SIZE = maxSize;
        hashMap = new HashMap<K, LRUEntry<K, ValueHolder<V>>>();
    }

    public ValueHolder get(K key) {
        LRUEntry<K, ValueHolder<V>> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        moveToFirst(entry);
        return entry.getValueHolder();
    }

    public boolean put(K key, V value) {
        LRUEntry<K, ValueHolder<V>> entry = getEntry(key);
        if (entry == null) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                hashMap.remove(last.getKey());
                removeLast();
            }
            entry = new LRUEntry<K, ValueHolder<V>>(key, new BasicValueHolder<V>(value));
        } else {
            entry.setValueHolder(new BasicValueHolder<V>(value));
        }
        moveToFirst(entry);
        hashMap.put(key, entry);
        return true;
    }

    public ValueHolder remove(K key) {
        LRUEntry<K, ValueHolder<V>> entry = getEntry(key);
        if (entry != null) {
            if (entry.getPre() != null) {
                entry.getPre().setNext(entry.getNext());
            }
            if (entry.getNext() != null) {
                entry.getNext().setPre(entry.getPre());
            }
            if (entry == first) {
                first = entry.getNext();
            }
            if (entry == last) {
                last = entry.getPre();
            }
            return hashMap.remove(key).getValueHolder();
        } else {
            return null;
        }
    }

    public void clear() {
        first = last = null;
        hashMap.clear();
    }

    private LRUEntry<K, ValueHolder<V>> getEntry(K key) {
        return hashMap.get(key);
    }

    private void moveToFirst(LRUEntry<K, ValueHolder<V>> entry) {
        if (entry == first) {
            return;
        }
        if (entry.getPre() != null) {
            entry.getPre().setNext(entry.getNext());
        }
        if (entry.getNext() != null) {
            entry.getNext().setPre(entry.getPre());
        }
        if (entry == last) {
            last = entry.getPre();
        }
        if (first == null || last == null) {
            first = last = entry;
            return;
        }
        entry.setNext(first);
        first.setPre(entry);
        first = entry;
        entry.setPre(null);
    }

    private void removeLast() {
        if (last != null) {
            last = last.getPre();
            if (last == null) {
                first = null;
            } else {
                last.setNext(null);
            }
        }
    }
}
