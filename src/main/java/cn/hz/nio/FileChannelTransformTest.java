package cn.hz.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelTransformTest {

	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = null;
		RandomAccessFile toFile = null;
		try{
			fromFile = new RandomAccessFile("fromFile.txt", "rw");
			FileChannel fromChannel = fromFile.getChannel();

			toFile = new RandomAccessFile("toFile.txt", "rw");
			FileChannel toChannel = toFile.getChannel();

			long position = 0;
			long count = fromChannel.size();

//			toChannel.transferFrom(fromChannel, position, count);
			fromChannel.transferTo(position, count, toChannel);
			
		}finally{
			fromFile.close();
			toFile.close();
		}
	}

}
