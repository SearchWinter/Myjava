package serializable.extend;

/**
 * @ClassName SubC
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/13  17:27
 **/
public class SubC extends SuperC {
    int subvalue;

    public SubC(int supervalue, int subvalue) {
        super(supervalue);
        this.subvalue = subvalue;
    }

    public String toString(){
        return super.toString()+"   sub:"+subvalue;
    }
}
