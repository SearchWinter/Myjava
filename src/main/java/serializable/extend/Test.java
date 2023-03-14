package serializable.extend;

import java.io.*;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/13  17:31
 **/
public class Test {
    public static void main(String[] args) {
        SubC subC = new SubC(100, 200);

        //子类序列化
        try {
            FileOutputStream fileout = new FileOutputStream("D:\\test1.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileout);
            outputStream.writeObject(subC);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //子类反序列化
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\test1.txt"));
            SubC subC1=(SubC) inputStream.readObject();
            System.out.println(subC1.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
