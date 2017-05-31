package cn.hz.test.algorithm;

import org.junit.Assert;
import org.junit.Test;

import cn.hz.algorithm.BinarySearch;

/**
 * @author wangxf
 * @date 2017年5月31日
 * 
 */
public class BinarySearchTest {

	private static Integer[] srcArray = new Integer[] { 1, 2, 5, 7, 11, 23, 24, 24, 25, 26, 41, 42, 43, 43, 43, 46, 53,
			64, 100 };

	@Test
	public void search() {
		Assert.assertEquals(0, BinarySearch.binarySearchInLoop(srcArray, 1));
		Assert.assertEquals(1, BinarySearch.binarySearchInLoop(srcArray, 2));
		Assert.assertEquals(2, BinarySearch.binarySearchInLoop(srcArray, 5));
		Assert.assertEquals(4, BinarySearch.binarySearchInLoop(srcArray, 11));
		Assert.assertEquals(5, BinarySearch.binarySearchInLoop(srcArray, 23));
		Assert.assertEquals(8, BinarySearch.binarySearchInLoop(srcArray, 25));
		
		Assert.assertEquals(0, BinarySearch.binarySearchInRecursion(srcArray, 1));
		Assert.assertEquals(1, BinarySearch.binarySearchInRecursion(srcArray, 2));
		Assert.assertEquals(2, BinarySearch.binarySearchInRecursion(srcArray, 5));
		Assert.assertEquals(4, BinarySearch.binarySearchInRecursion(srcArray, 11));
		Assert.assertEquals(5, BinarySearch.binarySearchInRecursion(srcArray, 23));
		Assert.assertEquals(8, BinarySearch.binarySearchInRecursion(srcArray, 25));
	}
}
