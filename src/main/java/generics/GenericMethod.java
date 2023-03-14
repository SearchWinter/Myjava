package generics;


public class GenericMethod {
    public static void main(String[] args) {
        Integer[] inArray={1,2,35,6,2};
        Double[] doubleArray={2.3,1.2,664.54,5353.53};
        printArray(inArray);
        printArray(doubleArray);

    }
    //泛型方法
    //在返回值类型前面添加了<E>,表示这是一个泛型方法，同时声明了一个泛型E
    //这个E可以出现在这个泛型方法的任意位置
    //泛型的数量也可以是任意个数
//    public <E,T,K> void  printArray(){}
    public static <E> void printArray(E[] inputArray){
        //输出数组
        for (E element :inputArray){
            System.out.print(element+"  ");
        }
        System.out.println();
    }

}
