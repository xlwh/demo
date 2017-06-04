package part1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServerTest {

	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//分配1024个座位
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//客车
		ServerSocketChannel ssc = null;
		//创建调度器
		Selector selector = null;
		try {
			selector = Selector.open();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//创建运输车
			ssc = ServerSocketChannel.open();
			//开启端口监听
			ssc.socket().bind(new InetSocketAddress(8080));
			//注册调度器
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			while(true) {
				Iterator<SelectionKey> it = (Iterator<SelectionKey>)selector.selectedKeys();
				while(it.hasNext()) {
					SelectionKey key = it.next();
					//处理客户端连接事件
					if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
						ServerSocketChannel sc = (ServerSocketChannel) key.channel();
						SocketChannel client = sc.accept();
						client.configureBlocking(false);
						//注册读数据事件
						client.register(selector, SelectionKey.OP_READ);
						//事件处理完毕，就删除事件
						it.remove();
					} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
						//处理数据读取事件
						SocketChannel sclient = (SocketChannel)key.channel();
						while(true) {
							//清理无用的数据
							buffer.clear();
							int n = sclient.read(buffer);
							if (n <=0){
								break;
							}
							buffer.flip(); //重置pos位
						}
						System.out.println("read done");
						it.remove();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
