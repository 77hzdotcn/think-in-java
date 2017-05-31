package cn.hz.algorithm;

import java.util.Arrays;

/**
 * 使用二分法插入元素
 * 
 * @author wangxf
 * @date 2017年5月31日
 * 
 */
public class BinaryInsert {

	/**
	 * 按二分法插入元素
	 * 
	 * @param container
	 * @param value
	 */
	public static Integer[] insert(Integer[] container, int value) {
		int index = lookup(container, value);
		// 扩充数组，移动元素
		container = Arrays.copyOf(container, container.length + 1);
		if (index != container.length - 1) {
			for (int m = container.length - 1; m > index; m--) {
				container[m] = container[m - 1];
			}
		}
		container[index] = value;
		return container;
	}

	/**
	 * 查找该元素应该插入的位置
	 * 
	 * @param container
	 * @param value
	 * @return
	 */
	public static int lookup(Integer[] container, int value) {
		if (container == null || container.length == 0) {
			return 0;
		}
		int nElems = container.length;
		int lower = 0;
		int upper = nElems - 1;
		int middle = (lower + upper) >> 1;
		while (lower <= upper) {
			int midValue = container[middle];
			if (value == midValue) {
				return middle;
			} else if (value < midValue) {
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
			middle = (lower + upper) >> 1;
		}
		if (upper > 0 && container[upper] < value) {
			return upper + 1;
		}
		return lower;

	}

}
