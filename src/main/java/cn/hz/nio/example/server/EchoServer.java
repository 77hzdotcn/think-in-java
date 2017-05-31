package cn.hz.nio.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Echo
 * 
 * @author wangxf
 * @date 2016年9月24日
 * 
 */
public class EchoServer {

	private static Set<SocketChannel> scs = new HashSet<>();

	public static void main(String[] args) throws IOException {
		SelectorProvider provider = SelectorProvider.provider();
		ServerSocketChannel channel = provider.openServerSocketChannel();
		channel.bind(new InetSocketAddress(9090));
		channel.configureBlocking(false);
		int validOps = channel.validOps();
		Selector selector = provider.openSelector();
		channel.register(selector, validOps);
		while (true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			while (iterator.hasNext()) {
				SelectionKey sk = iterator.next();
				if (sk.isAcceptable()) {
					System.out.println("accept");
					SocketChannel sc = channel.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
					scs.add(sc);
				} else if (sk.isReadable()) {
					System.out.println("read");
					SocketChannel sc = (SocketChannel) sk.channel();
					ByteBuffer bf = ByteBuffer.allocate(4);
					int l = sc.read(bf);
					bf.flip();

					for (SocketChannel s : scs) {
						s.write(bf);
						bf.flip();
					}
				}
				iterator.remove();
			}
		}
	}

}
