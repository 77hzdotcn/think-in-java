package cn.hz.test.my;

import java.io.IOException;
import java.io.InputStreamReader;

public class CharTest {

	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		int ch = in.read();
		System.out.println(ch);
		System.out.println(in.getEncoding());
		System.out.println((char)ch);

	}

}
