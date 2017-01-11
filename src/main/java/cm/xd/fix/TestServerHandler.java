package cm.xd.fix;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.SortedMap;

/**
 * Created by deepy on 2016-12-30.
 */
public class TestServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
            SortedMap<Integer, String> message = (SortedMap<Integer, String>) msg;
            if ("A".equals(message.get(35))) {
                System.out.println("login");
                ctx.writeAndFlush(msg);
            } else if ("0".equals(message.get(35))) {
                System.out.println("heartbeat");
            } else {
                System.out.println(message);
            }
            ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
