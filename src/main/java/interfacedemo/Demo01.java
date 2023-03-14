package interfacedemo;

public interface Demo01 {
    //被隐式的指定为 public static final
     int num=100;

//     Modifier 'protected' not allowed here
//   protected
//   private
    //接口里面的所有方法，都必须是抽象方法， public abstract
     void show();
   void display(String name,int age);

}
