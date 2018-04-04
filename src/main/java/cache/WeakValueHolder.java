package cache;

import java.lang.ref.WeakReference;

/**
 * Created by songdexv on 2018/3/5.
 */
public class WeakValueHolder<V> implements ValueHolder<V> {
    public WeakValueHolder(V value) {
        this.v = new WeakReference<V>(value);
    }

    private WeakReference<V> v;

    public V value() {
        return this.v.get();
    }
}
