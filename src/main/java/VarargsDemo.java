/**
 * @Description 可变参数
 * 注意的是可变参数只能作为函数的最后一个参数，但其前面可以有也可以没有任何其他参数。
 * @Date 2020/7/2  18:39
 **/
public class VarargsDemo {
    public static void main(String[] args) {
        int sum=add(10,23,23829,2372);
        System.out.println(sum);
    }
    public static int add(int... nums){
        int sum=0;
        for(int elements:nums){
            sum=sum+elements;
        }
        return sum;
    }
}
