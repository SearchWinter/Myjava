package dapper.csdnDemo;

/**
 * Created by anjunli on  2020/12/7
 **/
import java.util.ArrayList;
import java.util.List;


public class Test {

    //idWorker就是为了生成一个唯一的id
    private static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
    private static List<MessageVO> list = new ArrayList<MessageVO>();

    public static void main(String[] args) {
        long traceID =idWorker.nextId();
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("main方法开始了……", traceID, -1L, spanID);
        list.add(vo);
        doA(traceID,spanID);
        doB(traceID,spanID);

        treeMenuList(-1L,0);
    }

    public static void treeMenuList(Long pid,int d){
        int tmp =0;
        for(MessageVO mu: list){
            //遍历出父id等于参数的id
            if(mu.getpID().equals(pid)){
                for(int i=0;i<d;i++){
                    System.out.print("***");
                }
                System.out.println(mu);
                //递归遍历下一级
                if(tmp==0){
                    tmp=d+1;
                }
                treeMenuList(mu.getSpanID(),tmp);
            }
        }
    }


    private static void doA(long traceID,long pid) {
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("doA方法开始了……", traceID, pid, spanID);
        list.add(vo);
        doE(traceID,spanID);
    }

    private static void doB(long traceID,long pid) {
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("doB方法开始了……", traceID, pid, spanID);
        list.add(vo);
        doC(traceID,spanID);
        doD(traceID,spanID);
    }


    private static void doD(long traceID,long pid) {
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("doD方法开始了……", traceID, pid, spanID);
        list.add(vo);
    }


    private static void doC(long traceID,long pid) {
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("doC方法开始了……", traceID, pid, spanID);
        list.add(vo);
    }




    private static void doE(long traceID,long pid) {
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("doE方法开始了……", traceID, pid, spanID);
        list.add(vo);
        doF(traceID,spanID);

    }


    private static void doF(long traceID,long pid) {
        long spanID =idWorker.nextId();
        MessageVO vo = new MessageVO("doF方法开始了……", traceID, pid, spanID);
        list.add(vo);
    }

}