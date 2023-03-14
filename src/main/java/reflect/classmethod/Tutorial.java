package reflect.classmethod;

/**
 * @ClassName Tutorial
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/10  14:10
 **/
public class Tutorial {
    public int sum=10;
    private String name="dog";
    protected int id=110;

    public Tutorial() {
    }

    public Tutorial(int sum, String name, int id) {
        this.sum = sum;
        this.name = name;
        this.id = id;
    }

    public void display(){
        System.out.println("公有的");
    }
    private void show(){
        System.out.println("私有的");
    }
    protected void go(){
        System.out.println("受保护的");
    }
    @annodemo
    public void fly(String name,int meter){
        System.out.println(name+"可以飞"+meter+"米");
    }

}
