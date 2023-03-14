package casting;

/**
 * @Description 鼠标类，实现电子产品接口
 * @Date 2020/6/30  10:26
 **/
public class Mouse implements Electronics{
    //鼠标移动
    public void move(){
        System.out.println("move the mouse");
    }
    //鼠标点击
    public void onClick(){
        System.out.println("a click of the mouse");
    }
}
