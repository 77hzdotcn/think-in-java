package cn.hz.test.my;

import java.io.IOException;
import java.io.InputStreamReader;

public class CharTest {

	public static void main(String[] args) throws IOException {
		String s = new String();
		for(int i = 0; i < 10000; i++){
			s = s + i;
		}

	}

}
