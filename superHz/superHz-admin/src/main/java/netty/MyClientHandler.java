package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 通道绑定客户端处理器
 *
 * @author renxz
 * @date 2023/1/31 4:5b 下午
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送消息到服务端
        ctx.writeAndFlush(Unpooled.copiedBuffer("歪比巴卜~茉莉~Are you good~马来西亚~", CharsetUtil.UTF_8));
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接受服务端消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(String.format("receive server msg %s", byteBuf.toString(CharsetUtil.UTF_8)));
    }


}
