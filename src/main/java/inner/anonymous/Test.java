package inner.anonymous;

/**
 * @ClassName Test
 * @Description 匿名内部类---实现接口
 * @Author Li Anjun
 * @Date 2020/7/1  16:06
 **/
public class Test   {
    public static void main(String[] args) {
        Test test = new Test();
        test.sayHello();
    }

    public void sayHello(){
        //方法内部类实现EnglishGreeting实现HelloWorld接口
        class EnglishGreeting implements HelloWorld{
            String name="world";
            @Override
            public void greet() {
                greetSomeone("world");
            }

            @Override
            public void greetSomeone(String someone) {
                name=someone;
                System.out.println("Hello"+name);
            }
        }
        //匿名类实现HelloWorld接口
        HelloWorld frenchGreeting=new HelloWorld() {
            String name="tout le monde";
            @Override
            public void greet() {
                greetSomeone("tout le monde");
            }

            @Override
            public void greetSomeone(String someone) {
                name=someone;
                System.out.println("Salut"+name);
            }
        };
        //方法类继承HelloWorld接口，有自己的类名，定义完成后需要再用new关键字实例化才能使用
        HelloWorld englishGreeting = new EnglishGreeting();
        englishGreeting.greet();

        //frenchGreeting在定义的时候就已经实例化了，定义后可以直接使用
        //匿名类是一个表达式，因此在定义的最后用分号";"结束
        frenchGreeting.greet();
    }
}
