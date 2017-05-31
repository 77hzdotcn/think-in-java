package cn.hz.test.algorithm;

import org.junit.Assert;
import org.junit.Test;

import cn.hz.algorithm.BinaryInsert;

/**
 * @author wangxf
 * @date 2017年5月31日
 * 
 */
public class BinaryInsertTest {

	@Test
	public void insertTest() {
		Integer[] container = new Integer[0];
		container = BinaryInsert.insert(container, 5);
		container = BinaryInsert.insert(container, 1);
		container = BinaryInsert.insert(container, 2);
		container = BinaryInsert.insert(container, 10);
		container = BinaryInsert.insert(container, 8);
		container = BinaryInsert.insert(container, 9);
		container = BinaryInsert.insert(container, 6);
		container = BinaryInsert.insert(container, 3);
		container = BinaryInsert.insert(container, 4);
		container = BinaryInsert.insert(container, 0);
		container = BinaryInsert.insert(container, 7);

		Assert.assertArrayEquals(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, container);
	}

}
