package example.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class BootstrapExample {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        //创建一个Bootstrap类的实例，以创建和连接新的客户端Channel
        Bootstrap bs = new Bootstrap();
        //设置一个EventLoopGroup,提供用于处理Channel事件的EventLoop
        bs.group(group)
                //指定要使用的Channel实现
                .channel(NioSocketChannel.class)
                //设置用于Channel事件和数据处理的handler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext chc, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        ChannelFuture future = bs.connect(
                new InetSocketAddress("www.manning.com", 80)
        );
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()) {
                    System.out.println("Connection established");
                } else {
                    System.out.println("Connection attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}
