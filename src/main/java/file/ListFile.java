package file;

import java.io.File;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2020/9/25  14:30
 **/
public class ListFile {
    public static void main(String[] args) {
        File file = new File("E:\\test");
        File[] files = file.listFiles();
        for (File file1:files){
            System.out.println(file1);
        }
        ArrayList<String> arrayList = new ArrayList();
        ArrayList<String> filescanner = Filescanner(arrayList, files, 2);
        System.out.println(filescanner.size());
        for(String fileName:filescanner){
            System.out.println(fileName);
        }
    }

    //获取某个文件，往下指定深度的所有文件
    public static ArrayList<String> Filescanner(ArrayList<String> filesindepth, File files[], int depth) {
        if (depth > 0) {

            for (File file : files) {
                if(depth==1) {
                    filesindepth.add(file.getAbsolutePath());
                }
                if (file.isDirectory()) {
                    Filescanner(filesindepth, file.listFiles(), depth - 1);
                }
            }
        }
        return filesindepth;
    }

}
