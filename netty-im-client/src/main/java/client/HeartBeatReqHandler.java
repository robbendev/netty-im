package client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import protocol.Header;
import protocol.Message;
import protocol.MessageType;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(HeartBeatReqHandler.class);

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Message message = (Message) msg;
        // 握手成功，主动发送心跳消息
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP
                .value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000,
                    TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_RESP
                .value()) {
            LOG.info("Client receive server heart beat message : ---> "
                    + message);
        } else
            ctx.fireChannelRead(msg);
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            Message heatBeat = buildHeatBeat();
            LOG.info("Client send heart beat messsage to server : ---> "
                    + heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private Message buildHeatBeat() {
            Message message = new Message();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}
