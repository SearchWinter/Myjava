package inner;

//外部类和内部类具有相同的成员变量或方法  "类名.this.成员变量"
public class MemberInnerThis {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.show();
    }

    //外部类
    public static class Outer{
        String name="cat";
        //内部类
        public class Inner{
            String name ="dog";
            public void show(){
                System.out.println("外部类的名称是："+Outer.this.name);
                System.out.println("内部类的名称是"+name);
            }
        }
    }
}
