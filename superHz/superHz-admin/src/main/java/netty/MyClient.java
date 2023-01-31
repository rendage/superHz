package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty 实践 客户端
 *
 * @author renxz
 * @date 2023/1/31 4:44 下午
 */
public class MyClient {

    public static void main(String[] args) {
        // 创建线程组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        // 创建bootstrap对象
        Bootstrap bootstrap = new Bootstrap();
        try {
            // 设置线程组
            bootstrap.group(eventExecutors);
            // 设置通道实现类
            bootstrap.channel(NioSocketChannel.class);
            // 匿名内部类初始化通道
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 添加客户端处理器
                    socketChannel.pipeline().addLast(new MyClientHandler());
                }
            });

            System.out.println("客户端准备就绪 ～");

            // 连接服务端
            ChannelFuture channelFuture = null;
            channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
            // 对关闭通道监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }


}
