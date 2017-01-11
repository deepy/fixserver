package cm.xd.fix.protocol;

import cm.xd.fix.util.FIXParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.SortedMap;

/**
 * Created by deepy on 2017-01-02.
 */
public class FIXEncoder extends MessageToByteEncoder<SortedMap<Integer, String>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, SortedMap<Integer, String> in, ByteBuf out) throws Exception {
//        ctx.writeAndFlush(FIXParser.unparse(in).getBytes());
        out.writeBytes(FIXParser.unparse(in).getBytes());
    }
}