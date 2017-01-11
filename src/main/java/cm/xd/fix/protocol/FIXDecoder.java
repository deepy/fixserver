package cm.xd.fix.protocol;

import cm.xd.fix.util.FIXParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.ReferenceCountUtil;

import java.io.StringWriter;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by deepy on 2017-01-02.
 */
public class FIXDecoder extends ReplayingDecoder<Void> {

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        StringWriter message = new StringWriter();
        try {
            while (in.isReadable()) { // (1)
                char c = (char) in.readByte();
                message.write(c);
            }
        } finally {
            if (!message.toString().isEmpty()) {
                SortedMap<Integer, String> parse = FIXParser.parse(message.toString());
                out.add(parse);
            }
        }
    }
}
