package xun.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * ClassName: ChannelHandler
 * Package: xun.netty
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/16 22:27
 * @Version 1.0
 */
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到消息：" + buf.toString(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hello鸡毛啊，我是服务器".getBytes(StandardCharsets.UTF_8)));
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
