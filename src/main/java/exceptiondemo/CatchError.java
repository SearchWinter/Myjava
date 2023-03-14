package exceptiondemo;

/**
 * @ClassName CatchError
 * @Description 捕获error
 * @Author Li Anjun
 * @Date 2020/7/8  15:16
 **/
public class CatchError extends Error{
    public static void main(String[] args) throws Error {
        try{
            //new 一个自己的Error类型
            throw new CatchError("test catch error");
        }catch (Throwable t){
            System.out.println("step in the catch");
            t.printStackTrace();
        }
    }

    public CatchError() {
        super();
    }


    public CatchError(String message) {
        super(message);
    }

}