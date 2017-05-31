package cn.hz.algorithm;

/**
 * @author wangxf
 * @date 2017年5月31日
 * 
 */
public class BinarySearch {

	/**
	 * 循环二分查找算法
	 * 
	 * @param srcArray
	 * @param value
	 * @return
	 */
	public static int binarySearchInLoop(Integer[] srcArray, int value) {
		int lower = 0;
		int upper = srcArray.length - 1;
		while (lower <= upper) {
			int mid = (lower + upper) >> 1;
			int midValue = srcArray[mid];
			if (value == midValue) {
				return mid;
			} else if (value > midValue) {
				lower = mid + 1;
			} else {
				upper = mid - 1;
			}
		}
		return -1;

	}

	public static int binarySearchInRecursion(Integer[] srcArray, int value) {
		return binarySearchInRecursion(srcArray, value, 0, srcArray.length - 1);
	}

	private static int binarySearchInRecursion(Integer[] srcArray, int value, int lower, int upper) {
		if (lower > upper) {
			return -1;
		}
		int mid = (lower + upper) >> 1;
		int midValue = srcArray[mid];
		if (midValue == value) {
			return mid;
		} else if (midValue > value) {
			return binarySearchInRecursion(srcArray, value, lower, mid - 1);
		} else {
			return binarySearchInRecursion(srcArray, value, mid + 1, upper);
		}
	}

}
