package exceptiondemo;

/**
 * @ClassName RuntimeException
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/8  14:48
 **/
public class RuntimeException {
    public static void main(String[] args) {
        int[] arr={19,34,223};

        //NegativeArraySizeException
//        int[] arrs=new int[-1];

        //ArithmeticException
//        System.out.println(10 / 0);

        //ArrayIndexOutOfBoundsException
/*        try {
            System.out.println(arr[10]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }*/

        //ArrayStoreException
        //将String[]类型的数组向上转型成为Object[]数组，而真正在运行时，打印出来该数组的实际类型依旧是String[]数组，之后向该数组中插入一个Object类型的元素时，抛出异常。
        String[] array={"a","b"};
        Object[] objects=array;
        System.out.println(objects.getClass());
        objects[0]=new Object();

        //NumberFormatException
//        int length = Integer.parseInt("fafd");

    }
}
