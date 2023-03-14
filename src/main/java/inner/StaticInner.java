package inner;


public class StaticInner {
    String name="dog";

    static String  display="sing";

    public static void main(String[] args) {
        //静态内部类是可以直接new,内部类对象的。
        inner inner = new inner();
        inner.show();
        inner.display();
    }
    public static class inner{
        //如果外部类的静态成员与内部类的静态成员相同， 可以通过"类名.静态成员"来访问外部类的静态成员；
        // 如果不同，可以直接调用外部类的静态成员名。
        static  String display="dance";
        public void display(){
            System.out.println(StaticInner.display);
            System.out.println(inner.display);
        }
        public void show(){
            //不能直接访问外部类的非静态成员  new 外部类().成员
            System.out.println("外部类的名称是"+new StaticInner().name);
        }
    }
}
