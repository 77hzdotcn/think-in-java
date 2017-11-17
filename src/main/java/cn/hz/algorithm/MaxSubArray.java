package cn.hz.algorithm;

/**
 * 最大子数组求解
 * 
 * @author wangxf
 * @date 2017年9月10日
 * 
 */
public class MaxSubArray {

	/**
	 * 最大子数组的线性算法
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] findMaxSubArrayWithLine(int[] arr) {
		int maxSum = arr[0];
		int sum = 0;
		int low = 0;
		int high = 0;
		for (int i = 0, l = arr.length; i < l; i++) {
			if (sum < 0) {
				sum = arr[i];
				low = i;
			} else {
				sum += arr[i];
			}
			if (sum > maxSum) {
				maxSum = sum;
				high = i;
			}
		}
		return new int[] { low, high, maxSum };
	}

	/**
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @return 数组，分别为下标的最小、最大值、最大子数组和
	 */
	public static int[] findMaxSubArray(int[] arr, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			int[] r1 = findMaxSubArray(arr, low, mid);
			int[] r2 = findMaxSubArray(arr, mid + 1, high);
			int[] r3 = findMaxCrossMidSubArray(arr, low, mid, high);

			int r1MaxSum = r1[2];
			int r2MaxSum = r2[2];
			int r3MaxSum = r3[2];
			if (r1MaxSum >= r2MaxSum && r1MaxSum >= r3MaxSum) { // 左边最大子数组和大
				return r1;
			} else if (r2MaxSum >= r1MaxSum && r2MaxSum >= r3MaxSum) { // 右边最大子数组和大
				return r2;
			} else {// 中间最大子数组和大
				return r3;
			}
		} else {
			return new int[] { low, high, arr[0] };
		}

	}

	public static int[] findMaxCrossMidSubArray(int[] arr, int low, int mid, int high) {
		int leftMaxSum = Integer.MIN_VALUE;
		int leftMaxIndex = mid;
		int rightMaxIndex = mid;
		int rightMaxSum = Integer.MIN_VALUE;
		int sum = 0;
		int i = mid;
		while (i >= low) {
			sum += arr[i];
			if (sum > leftMaxSum) {
				leftMaxSum = sum;
				leftMaxIndex = i;
			}
			i--;
		}
		sum = 0;
		i = mid + 1;
		while (i <= high) {
			sum += arr[i];
			if (sum > rightMaxSum) {
				rightMaxSum = sum;
				rightMaxIndex = i;
			}
			i++;
		}

		return new int[] { leftMaxIndex, rightMaxIndex, (leftMaxSum + rightMaxSum) };
	}

	public static void main(String[] args) {
		int[] source = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		int[] result = findMaxSubArrayWithLine(source);
		System.out.println(result[0] + " " + result[1] + " " + result[2]);
		source = new int[] { 1, 2, 3, 4, 5 };
		result = findMaxSubArrayWithLine(source);
		System.out.println(result[0] + " " + result[1] + " " + result[2]);
		source = new int[] { -5, -4, -3, -2, -1 };
		result = findMaxSubArrayWithLine(source);
		System.out.println(result[0] + " " + result[1] + " " + result[2]);

	}

}
