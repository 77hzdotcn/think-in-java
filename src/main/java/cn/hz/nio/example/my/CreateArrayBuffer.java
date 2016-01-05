package cn.hz.nio.example.my;

import java.nio.ByteBuffer;

public class CreateArrayBuffer {

	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		ByteBuffer bb = ByteBuffer.wrap(buffer);
		
		bb.put((byte)'a');
		bb.put((byte)'b');
		bb.put((byte)'c');
		
		buffer[2] = (byte)'d';
		
		bb.flip();
		
		System.out.println((char)bb.get());
		System.out.println((char)bb.get());
		System.out.println((char)bb.get());
	}

}
