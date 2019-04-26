package protocol;

/**
 * @Author robbendev
 * @Description //TODO
 * @Date 2019/4/27
 **/
public final class Message {
    //消息头
    private Header header;
    //消息体
    private Object body;

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                '}';
    }
}
