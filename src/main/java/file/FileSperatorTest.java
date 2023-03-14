package file;

import java.io.File;

/**
 * Created by anjunli on  2021/5/17
 **/
public class FileSperatorTest {
    public static void main(String[] args) {
        File file = new File(File.separator + "demo");
        System.out.println(file.getPath());

        System.out.println(System.lineSeparator());
    }
}
