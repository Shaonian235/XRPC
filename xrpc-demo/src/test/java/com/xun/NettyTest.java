package com.xun;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * ClassName: NettyTest
 * Package: com.xun
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/16 19:36
 * @Version 1.0
 */
public class NettyTest {

    @Test
    public void testCompositeByteBuf() {
        ByteBuf header = Unpooled.buffer();
        ByteBuf body = Unpooled.buffer();
        //通过逻辑组装，而不是物理拷贝，来实现在Jvm中的零拷贝
        CompositeByteBuf httpBuf = Unpooled.compositeBuffer();
        httpBuf.addComponents(header,body);

    }
    @Test
    public void testWrapper() {
       byte [] buf = new byte[1024];
       byte [] buf2 = new byte[1024];
       //共享byte数组内容，而不是拷贝，这也算实现了零拷贝
       ByteBuf byteBuf = Unpooled.wrappedBuffer(buf,buf2);
        System.out.println(byteBuf.readableBytes());

    }

    @Test
    public void testSlice() {
        byte [] buf = new byte[1024];
        byte [] buf2 = new byte[1024];
        //共享byte数组内容，而不是拷贝，这也算实现了零拷贝
        ByteBuf byteBuf = Unpooled.wrappedBuffer(buf,buf2);
        ByteBuf header = byteBuf.slice(0, 1024);
        ByteBuf body = byteBuf.slice(1024, 2048);
        System.out.println(byteBuf.readableBytes());

    }
}
