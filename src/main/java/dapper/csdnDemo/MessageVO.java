package dapper.csdnDemo;

/**
 * Created by anjunli on  2020/12/7
 **/
public class MessageVO {
    private String messg; //内容
    private Long traceID; //全局唯一的 TraceID
    private Long pID; //上级id
    private Long spanID; //全局唯一的 SpanID


    public MessageVO() {

    }


    public MessageVO(String messg, Long traceID, Long pID, Long spanID) {
        this.messg = messg;
        this.traceID = traceID;
        this.pID = pID;
        this.spanID = spanID;
    }


    public String getMessg() {
        return messg;
    }


    public void setMessg(String messg) {
        this.messg = messg;
    }


    public Long getTraceID() {
        return traceID;
    }


    public void setTraceID(Long traceID) {
        this.traceID = traceID;
    }


    public Long getpID() {
        return pID;
    }


    public void setpID(Long pID) {
        this.pID = pID;
    }


    public Long getSpanID() {
        return spanID;
    }


    public void setSpanID(Long spanID) {
        this.spanID = spanID;
    }


    @Override
    public String toString() {
        return  messg;
    }


}
