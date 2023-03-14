package inner.anonymous;

/**
 * @ClassName HelloWorldAnonymousClasses
 * @Description 官网案例
 * @Author Li Anjun
 * @Date 2020/7/1  16:57
 **/
public class HelloWorldAnonymousClasses {

    /**
     * 包含两个方法的HelloWorld接口
     * */


    interface HelloWorld {
        public abstract void greet();
        public abstract void greetSomeone(String someone);
    }

    public void sayHello() {

        // 1、局部类EnglishGreeting实现了HelloWorld接口
        class EnglishGreeting implements HelloWorld {
            String name = "world";

            @Override
            public void greet() {
                greetSomeone("world");
            }
            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }


        // 2、匿名类实现HelloWorld接口
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            @Override
            public void greet() {
                greetSomeone("tout le monde");
            }
            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };
        HelloWorld englishGreeting = new EnglishGreeting();
        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
    }

    public static void main(String[] args) {
        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }
}