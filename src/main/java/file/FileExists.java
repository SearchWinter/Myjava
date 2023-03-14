package file;

import java.io.File;

/**
 * @Description delete()方法，如果是一个目录，目录下面必须为空才能删除
 * @Date 2020/10/20  16:54
 **/
public class FileExists {
    public static void main(String[] args) {
        //linux下的路径，项目中的路径
        File file = new File("/data/taf/result/BaseStat/");
        if (file.exists()) {

            //是一个文件夹，为空直接删，不为空
            if (file.isDirectory()) {
                if (file != null) {

                } else {
                    file.delete();
                }
            }
        } else {
            System.out.println("此文件不存在");
        }
    }
}
