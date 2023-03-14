import java.util.ArrayList;

/**
 * Created by anjunli on  2021/3/12
 * hprof文件的生成和分析，生成的hprof文件就在这个项目下
 * C:\Program Files\Java\jdk1.8.0_221\bin\JVisualvm.exe
 * -Xms100m -Xmx100m -XX:+HeapDumpOnOutOfMemoryError
 **/
public class HprofTest {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        try {
            while (true) {
                users.add(new User("tom", "mail", "25"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class User{
    private String name;
    private String sex;
    private String age;

    public User(String name, String sex, String age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
