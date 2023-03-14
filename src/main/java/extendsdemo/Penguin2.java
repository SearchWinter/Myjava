package extendsdemo;

/**
 * @Description 多重继承
 * Animal里面的eat()方法被重写了，在penguin2里面怎么调用Animal里面的eat()方法
 * 只能在父类里面将第一个父类的eat() 包装成一个新的feat()方法，然后再调用feat()方法
 * @Date 2020/7/1  9:47
 **/
public class Penguin2 extends Penguin {
    public String name="dogfs";
    public static void main(String[] args) {
        Penguin2 penguin2 = new Penguin2("penguinTwo",333);
        //Animal   Penguin里面都有一个eat方法，调用哪一个
        //调用penguin的eat()方法，强制转换也是
        penguin2.eat();
        ((Animal)penguin2).eat();
        System.out.println("*******************");
        System.out.println( ((Animal)penguin2).name);
        System.out.println(penguin2.name);

        //在penguin里面使用feat()调用Animal的eat()方法，在penguin2里面调用feat()方法实际运行的是Animal的eat()方法
        penguin2.feat();
        //没有被重写的sleep()方法可以直接调用
        penguin2.sleep();
    }
    public Penguin2(String myName, int myid) {
        super(myName, myid);
    }
}
