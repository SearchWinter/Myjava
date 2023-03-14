package extendsdemo;

/**
 * @Description 验证一个类可以被多个类继承
 * @Date 2020/7/1  11:40
 **/
public class Dog extends Animal {
    public String name="dogfs";
    public static void main(String[] args) {
        Dog dog = new Dog("dog",1111);
        dog.eat();

        //向上转型：父类引用指向子类对象
        Animal a=new Dog();
        //调用的是Dog继承或者覆盖Animal的方法,不能调用子类特有的方法
        a.eat();

        //向下转型：将指向子类对象的父类引用赋值给子类引用,需要强制转换
        Dog dog1=(Dog) a;
        dog1.eat();
        //可以调用子类特有的方法
        dog1.display();

        char[] crr={'a','b','c'};
        int[] irr={100,200,400};

        //int -> char 强制转换
        int num1=10;
        char c1=(char)num1;
        dog1.CharOne(c1);
//        dog1.CharDemo(irr);

        //char ——> int
        char c2='b';
        dog1.IntOne(c2);
//        dog1.IntDemo(crr);

    }
    public Dog(){

    }

    public Dog(String myName, int myid) {
        super(myName, myid);
    }

    @Override
    public void eat() {
        System.out.println("dog的eat()方法");
    }

    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public void introduction() {
        super.introduction();
    }

    public void display(){
        System.out.println("dog的display方法");
    }
}
