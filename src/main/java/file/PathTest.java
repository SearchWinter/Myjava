package file;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by anjunli on  2020/11/4
 **/
public class PathTest {
    @Test
    public void putDemo(){
        Path path = Paths.get("/data/app/taf/remote_app_log/Push/ConnectServer", "Push.ConnectServer_push_req_20201101.log");
    System.out.println(path);
    }
}
