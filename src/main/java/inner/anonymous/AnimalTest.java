package inner.anonymous;


/**
 * @ClassName AnimalTest
 * @Description 匿名子类---继承父类
 * @Author Li Anjun
 * @Date 2020/7/1  16:49
 **/
public class AnimalTest {
    private final String ANIMAL="动物";

    public static void main(String[] args) {
        AnimalTest animalTest=new AnimalTest();
        animalTest.print();
    }

    public void accessTest(){
        System.out.println("匿名内部类访问其外部类方法");
    }

    class Animal{
        private String name;

        public Animal(String name){
            this.name=name;
        }

        public void printAnimalName(){
            System.out.println(bird.name);
        }
    }
    //鸟类，匿名子类 继承Animal
    Animal bird = new Animal("鸽子") {
        @Override
        public void printAnimalName() {
            //访问外部类的方法
            accessTest();
            //访问外部类final修饰的变量
            System.out.println(ANIMAL);
            super.printAnimalName();
        }
    };
    public void print(){
        bird.printAnimalName();
    }
}
