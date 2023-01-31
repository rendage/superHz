package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty 框架实践 服务端
 *
 * @author renxz
 * @date 2023/1/31 4:15 下午
 */
public class MyServer {

    public static void main(String[] args) {
        // 创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务启动对象 ，设置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置连个线程组
            bootstrap.group(bossGroup, workerGroup);
            // 设置服务端通道实现类
            bootstrap.channel(NioServerSocketChannel.class);
            // 设置线程队列得到的连接个数
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            // 设置保持活动连接状态
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 使用匿名内部类初始化通道对象
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 设置管道处理器
                    socketChannel.pipeline().addLast(new MyServerHandler());
                }

            });

            System.out.println("netty 服务端准备就绪 。。。");

            // 绑定端口 启动的服务端

            ChannelFuture channelFuture = bootstrap.bind(6666).sync();
            // 对关闭端口进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}
