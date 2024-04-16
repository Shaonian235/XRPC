package xun.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: Client
 * Package: xun.netty
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/16 20:29
 * @Version 1.0
 */
public class Client {
    public void run(){
        //定义线程池，EventLoopGroup
        NioEventLoopGroup eventLoop = new NioEventLoopGroup();
        //启动客户端
        Bootstrap bootstrap = new Bootstrap();
        bootstrap= bootstrap.group(eventLoop)
                .remoteAddress(new InetSocketAddress("127.0.0.1", 8080))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientChannelHandler());
                    }
                });
        //尝试连接服务器

        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect().sync();
            channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("hello,world".getBytes(StandardCharsets.UTF_8)));
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new Client().run();
    }
}
