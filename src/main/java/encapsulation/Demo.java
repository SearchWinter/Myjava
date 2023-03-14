package encapsulation;

//封装
public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.display(new User("cat",21,"wuhan"));
    }
    public void display(User user){
        System.out.println(user.getAddress());
    }

    public void show(User user){
        System.out.println(user.getName());
    }
}
