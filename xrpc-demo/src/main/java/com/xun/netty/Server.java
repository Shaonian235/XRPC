package xun.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ClassName: Server
 * Package: xun.netty
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/16 21:06
 * @Version 1.0
 */
public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = null;
        EventLoopGroup worker = null;
        try {
            bossGroup = new NioEventLoopGroup(2);
            worker = new NioEventLoopGroup(10);
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerChannelHandler());
                        }
                    })
                    .bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully().sync();
            worker.shutdownGracefully().sync();
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(8080);
        server.start();
    }
}
