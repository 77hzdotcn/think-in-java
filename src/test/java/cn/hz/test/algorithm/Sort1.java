package cn.hz.test.algorithm;

import java.util.Random;

import cn.hz.algorithm.Sort;

/**
 * @author wangxf
 * @date 2017年9月9日
 * 
 */
public class Sort1 {

	public static void main(String[] args) {
		int[] source = new int[10];
		System.out.println("source:");
		for (int i = 0; i < 10; i++) {
			source[i] = new Random().nextInt(20);
			System.out.print(source[i] + " ");
		}
		System.out.println();
		Sort.insert(source);
		System.out.println("result:");
		for (int i : source) {
			System.out.print(i + " ");
		}

	}

}
