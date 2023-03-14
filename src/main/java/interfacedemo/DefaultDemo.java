package interfacedemo;

public interface DefaultDemo {
    //默认方法
    default String setTime(String newTime){
        return "time set to "+newTime;
    }
    //静态方法
    static void hello(){
        System.out.println("New static Method");
    }
}
