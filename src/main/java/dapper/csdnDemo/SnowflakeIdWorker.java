package dapper.csdnDemo;

/**
 * Created by anjunli on  2020/12/7
 **/
public class SnowflakeIdWorker {
    private int a;
    private int b;

    public SnowflakeIdWorker(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public long nextId(){
        return  Math.round(Math.random()*1000000);
    }
}
