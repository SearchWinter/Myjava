package structure.map.ConcHashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by anjunli on  2020/11/12
 * 因为多线程环境下，使用Hashmap进行put操作可能会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap。
 **/
public class NotSafe {
  public static void main(String[] args) {
      HashMap<String, String> hashMap = new HashMap<>(2);
      for (int i=0;i<10000;i++){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  hashMap.put(UUID.randomUUID().toString(),"");
              }
          }).start();
      }
  }

  /** UUID 通用唯一识别码*/
  @Test
    public void uuidTest(){
    System.out.println(UUID.randomUUID().toString());
  }
}
