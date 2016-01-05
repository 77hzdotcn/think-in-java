package cn.hz.nio.example.my;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	
	private static String infile = "nio.txt";
	private static String outfile = "out.txt";

	public static void main(String[] args) throws IOException {
		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);
		
		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();
		
		ByteBuffer bb = ByteBuffer.allocate(1024);
		while(true){
			bb.clear();
			int r = fcin.read(bb);
			if(r == -1){
				break;
			}
			bb.flip();
			fcout.write(bb);
		}
	}

}
