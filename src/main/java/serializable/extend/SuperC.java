package serializable.extend;

import java.io.Serializable;

/**
 * @ClassName SuperC
 * @Description 父类实现接口后，所有派生类的属性都会被序列化
 * @Author Li Anjun
 * @Date 2020/7/13  17:23
 **/
public class SuperC implements Serializable {
    int supervalue;

    public SuperC() {
    }

    public SuperC(int supervalue) {
        this.supervalue = supervalue;
    }
    public String toString(){
        return "supervalue"+supervalue;
    }
}
