package enumdemo;

import java.lang.annotation.Retention;

/**
 * @ClassName SwitchCaseDemo
 * @Description switch..case..
 * @Author Li Anjun
 * @Date 2020/7/9  13:35
 **/
public class SwitchCaseDemo {
    public static void main(String[] args) {
        //char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
        switch (12){
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            default:
                System.out.println("default num");
        }
    }
}
