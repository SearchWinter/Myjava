package structure;

import java.util.Comparator;

/**
 * @ClassName ComparatorDemo
 * @Description 自定义比较器
 * @Author Li Anjun
 * @Date 2020/7/3  18:12
 **/
public class ComparatorDemo {
    public static void main(String[] args) {
    compNum compNum = new compNum();
    Integer c1=0;
    Integer c2=10;
    if (compNum.compare(c1,c2)==1){
        System.out.println(c1+"比"+c2+"大");
    }else {
        System.out.println(c1+"比"+c2+"小");
    }
}

static class compNum implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1>o2){
            return 1;
        }else {
            return -1;
        }
    }
}
}
