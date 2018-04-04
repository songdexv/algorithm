package cache;

/**
 * Created by songdexv on 2018/3/5.
 */
public class BasicValueHolder<V> implements ValueHolder<V> {
    private V value;

    public BasicValueHolder(V value) {
        this.value = value;
    }

    public V value() {
        return this.value;
    }
}
