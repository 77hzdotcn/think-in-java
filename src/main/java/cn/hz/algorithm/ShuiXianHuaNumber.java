package cn.hz.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxf
 * @date 2016年7月24日
 * 
 */
public class ShuiXianHuaNumber {

	private static BigDecimal[] table = new BigDecimal[10];

	public static List<BigDecimal> getNBitNumber(int n) {
		List<BigDecimal> retList = new ArrayList<>();
		BigDecimal min = getMin(n);
		BigDecimal max = getMax(n);
		for (BigDecimal i = min; i.compareTo(max) < 0; i.add(BigDecimal.valueOf(1))) {
			if (isSXH(i)) {
				retList.add(i);
			}
		}
		return retList;
	}

	public static boolean isSXH(BigDecimal num) {
		char[] charArray = String.valueOf(num).toCharArray();
		BigDecimal sum = new BigDecimal(0);
		for (int i = 0; i < charArray.length; i++) {
			int subNum = Integer.parseInt(String.valueOf(charArray[i]));
			BigDecimal subSum = table[subNum];
			if (subNum > 0) {
				if (subSum == null) {
					subSum = new BigDecimal(subNum).pow(charArray.length);
					table[subNum] = subSum;
				}

			}
			sum.add(subSum);
		}
		if (num == sum) {
			return true;
		}
		return false;
	}

	private static BigDecimal getMin(int length) {
		char[] arr = new char[length];
		Arrays.fill(arr, '0');
		arr[0] = '1';
		return new BigDecimal(arr);
	}

	private static BigDecimal getMax(int length) {
		char[] arr = new char[length];
		Arrays.fill(arr, '9');
		return new BigDecimal(arr);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(getNBitNumber(3));
		System.out.println("time used : " + (System.currentTimeMillis() - start));

	}

}
