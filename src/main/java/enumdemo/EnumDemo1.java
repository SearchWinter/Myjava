package enumdemo;

/**
 * @ClassName EnumDemo1
 * @Description 枚举类型测试
 * @Author Li Anjun
 * @Date 2020/7/1  23:40
 **/
public class EnumDemo1  {
    public static void main(String[] args) {
        System.out.println(        printDemo(Constants.blue));
        printAll();
    }

    //通常和switch...case...一起使用
    //printDemo 参数类型是一个枚举类型
    public static String  printDemo(Constants cs){
        switch(cs){
            case red:
                System.out.println("颜色是："+Constants.red);
                return "reddd";
            default:
                return "defaultmethod";
        }
    }
    //输出所有枚举类型  values()返回的是一个数组
    public static void printAll(){
        for(Constants all:Constants.values()){
            System.out.println(all);
        }
    }
}
