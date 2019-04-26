package protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author robbendev
 * @Description //TODO
 * @Date 2019/4/27
 **/
public final class Header {
    //crcCode校验
    private int crcCode = 0xbef001;
    //消息长度
    private int length;
    //会话Id
    private long sessionId;
    //消息类型
    private byte type;
    //消息优先级
    private byte priority;
    //附件
    private Map<String, Object> attachment = new HashMap<String, Object>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }
}
