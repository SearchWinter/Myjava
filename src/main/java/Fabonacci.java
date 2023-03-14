/**
 * @Description 斐波拉契数列
 * @Date 2020/6/29  16:37
 **/
public class Fabonacci {
    public static void main(String[] args) {
        for(int j=1;j<=50;j++){
            System.out.println(getFibo(j));
        }
    }
    public static int getFibo(int i){
        if(i==1||i==2){
            return 1;
        }else{
            return getFibo(i-1)+getFibo(i-2);
        }
    }
}
