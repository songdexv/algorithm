package cache.entity;

/**
 * Created by songdexv on 2018/3/5.
 */
public class User {
    private String name;

    public User(){

    }

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
