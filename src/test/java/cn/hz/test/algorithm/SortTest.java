package cn.hz.test.algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import cn.hz.algorithm.Sort;

public class SortTest {

	private int[] arr = new int[20];

	@Before
	public void init() {
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			arr[i] = r.nextInt(100);
		}
		System.out.print("init : ");
		print();

	}

	@Test
	public void bubble() {
		Sort.bubble(arr);
		System.out.print("bubble : ");
		print();
	}

	@Test
	public void quick() {
		Sort.quick(arr);
		System.out.print("quick : ");
		print();
	}

	@Test
	public void merge() {
		Sort.merge(arr);
		System.out.print("merge : ");
		print();
	}

	private void print() {
		System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]")));
	}

}
