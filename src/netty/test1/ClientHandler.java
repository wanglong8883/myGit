package netty.test1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class  ClientHandler extends ChannelHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        try {
            ByteBuf buf=(ByteBuf)msg;
            byte[] req=new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body=new String(req,"utf-8");
            System.out.println("Client :"+body);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
