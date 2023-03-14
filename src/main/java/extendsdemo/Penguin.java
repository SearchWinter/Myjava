package extendsdemo;

/**
 * @Description TODO
 * @Date 2020/7/1  9:46
 **/
public  class Penguin extends Animal {
    public static void main(String[] args) {
        Penguin penguin = new Penguin("cat",20);
        penguin.introduction();
        penguin.feat();

        Animal animal=new Animal();
        animal.eat();

    }
    //子类不继承父类的构造器，如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super关键字调用父类的构造器并配以适当的参数列表。
    public Penguin(String myName, int myid) {
        super(myName, myid);
    }
    public void feat(){
        this.eat();
        super.eat();
    }
    //调用子类和父类的eat方法
    @Override
    public void eat(){
        System.out.println("Penguin类自己的eat方法");
    }

    @Override
    public void introduction() {
        super.introduction();
    }
}
