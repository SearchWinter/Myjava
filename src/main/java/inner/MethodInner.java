package inner;


public class MethodInner {
    public static void main(String[] args) {
        new MethodInner().show();
    }

    //外部类中的方法
    public void show() {
        final int a = 25;
        int b = 20;
        //方法内部类
        class MInner {
            int c = 2;

            public void print() {
                System.out.println("访问外部类方法里面的常量" + a);
                System.out.println("访问内部类里面的变量" + c);
            }
        }
            //只能在外部类方法里面使用
        MInner mInner = new MInner();
        mInner.print();
        }
    }
