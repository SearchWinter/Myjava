package serializable;

import java.io.Serializable;


public class Employee implements Serializable {
    public static final long serialVersionUID = 1L;
    public String name;
    public String address;
    //使属性不被序列化进去
    public int ssn;
    private String type;
    private String type2;

    public static String test = "demo";

    public void check() {
        System.out.println(name + " " + address);
    }

}
