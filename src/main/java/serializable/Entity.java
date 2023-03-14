package serializable;

import java.io.*;

/**
 * @ClassName Entity
 * @Description 对敏感字段加密
 * @Author Li Anjun
 * @Date 2020/7/13  16:27
 **/
public class Entity implements Serializable  {
    private static final long serialVersionUID=1L;
    private String password="pass";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void writeObject(ObjectOutputStream output){
        try {
            //检索用于缓冲要写入流的持久字段的对象。
            ObjectOutputStream.PutField putFields = output.putFields();
            System.out.println("原密码为："+password);
            //模拟加密
            password="entitypass";
            //将password加密后的值，赋值给名为password的字段
            putFields.put("password",password);
            System.out.println("加密后的密码为："+password);
            //将缓冲字段写入流
            output.writeFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream input){
        try {
            //从流中读取持久字段，并使它们按名称可用。
            ObjectInputStream.GetField readFields = input.readFields();
            Object object = readFields.get("password", "");
            System.out.println("要解密的字段为："+object.toString());
            //模拟解密过程
            password="pass";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\entity.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(new Entity());
            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\entity.txt"));
            Entity entity=(Entity) in.readObject();
            System.out.println("解密后的密码为："+entity.getPassword());
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
