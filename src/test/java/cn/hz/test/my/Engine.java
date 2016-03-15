package cn.hz.test.my;

import java.math.BigInteger;

public class Engine {
	
	public static void main(String[] args){
		int ADDRESS_BITS_PER_WORD = 6;
	    int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
	    System.out.println(BITS_PER_WORD);
	    
	    BigInteger a = BigInteger.valueOf(6L);
	    BigInteger b1 = a.flipBit(1);
	    BigInteger b2 = a.flipBit(2);
	    BigInteger b3 = a.flipBit(3);
	    System.out.println(b1.longValue());
	    System.out.println(b2.longValue());
	    System.out.println(b3.longValue());
	}

}
