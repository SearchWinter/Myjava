package inner;

//成员内部类
public class MemberInner {
    public static void main(String[] args) {
        //使用外部类对象创建内部类对象
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.test();
    }
    //外部类
    public static class Outer{
        //外部类私有属性
        private int a=99;
        protected int b=2323;
        int c=42304;
        //内部类
        public class Inner{
            //内部类方法可以调用外部类的数据，不受访问控制符的限制
            public void test(){
                System.out.println("访问外部类中的a"+a);
                System.out.println("访问内部类里面的b"+b);
                System.out.println(c);
            }
        }
    }
}
