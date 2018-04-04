package cache;

import org.junit.Test;
import cache.entity.User;


/**
 * Created by songdexv on 2018/3/5.
 */
public class DataStoreTest {
    @Test
    public void testWeakValueDataStore() throws InterruptedException {
        User user = new User();
        user.setName("test");
        DataStore<String, User> dataStore = new WeakValueDataStore<String, User>();
        dataStore.put("test", user);

        user = null;
        System.out.println("hello " + dataStore.get("test").value().getName());
        System.gc();
        Thread.sleep(10000);
        System.out.println("hello " + dataStore.get("test").value());
    }

    @Test
    public void testLRUDataStore() {
        DataStore<String, User> dataStore = new LRUDataStore<String, User>(3);
        dataStore.put("test1", new User("test1"));
        dataStore.put("test2", new User("test2"));
        dataStore.put("test3", new User("test3"));
        dataStore.put("test4", new User("test4"));
        System.out.println(dataStore.get("test2"));
        System.out.println(dataStore);
    }
}
