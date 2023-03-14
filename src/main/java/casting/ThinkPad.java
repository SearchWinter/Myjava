package casting;

/**
 * @ClassName ThinkPad
 * @Description 笔记本类，实现电子产品接口
 * @Author Li Anjun
 * @Date 2020/6/30  10:23
 **/
public class ThinkPad implements Electronics {
    //引导方法
    public void boot(){
        System.out.println("welcome,I am ThinkPad");
    }
    //使用Thinkpad编程
    public void program(){
        System.out.println("using Thinkpad program");
    }
}
