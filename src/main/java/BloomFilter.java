import java.util.BitSet;

/**
 * Created by songdexv on 2018/3/29.
 */
public class BloomFilter {
    private static final int DEFAULT_SIZE = 2 << 24;
    private static final int[] seeds = new int[] {7, 11, 13, 31, 37, 61, 73, 87};

    private BitSet bitSet = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] funcs = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            funcs[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    public void add(String value) {
        for (SimpleHash hash : funcs) {
            bitSet.set(hash.hash(value), true);
        }
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }

        boolean ret = true;
        for (SimpleHash hash : funcs) {
            ret = ret && bitSet.get(hash.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {
        private int capacity;
        private int seed;

        public SimpleHash(int capacity, int seed) {
            this.capacity = capacity;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int length = value.length();
            for (int i = 0; i < length; i++) {
                result = result * seed + value.charAt(i);
            }
            return (capacity - 1) & result;
        }
    }
}
