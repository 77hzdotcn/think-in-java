package cn.hz.algorithm;

/**
 * 排序算法
 * 
 * @author wangxf
 * @date 2016年6月28日
 */
public class Sort {

	/**
	 * 插入排序
	 * 
	 * @param arr
	 */
	public static void insert(int[] arr) {
		int length = arr.length;
		for (int i = 1; i < length; i++) {
			int num = arr[i];
			int m = i;
			while (m >= 1 && num < arr[m - 1]) {
				arr[m] = arr[m - 1];
				m--;
			}
			arr[m] = num;
		}

	}

	/**
	 * 冒泡
	 * 
	 * @param arr
	 */
	public static void bubble(int[] arr) {
		boolean flag = true; // 针对已排序数列做优化
		for (int i = 0, l = arr.length - 1; i < l; i++) {
			if (flag) {
				for (int j = 0; j < l - i; j++) {
					flag = false;
					if (arr[j] > arr[j + 1]) {
						int temp = arr[j];
						arr[j] = arr[j + 1];
						arr[j + 1] = temp;
						flag = true;
					}
				}
			} else {
				break;
			}
		}
	}

	/**
	 * 快速
	 * 
	 * @param arr
	 */
	public static void quick1(int[] arr) {
		new QuickSorter(arr).sort();
	}

	/**
	 * 快速排序
	 * 
	 * @param arr
	 */
	public static void quick(int[] arr) {
		_quick(arr, 0, arr.length - 1);
	}

	/**
	 * 
	 * @param arr
	 * @param p
	 *            起点序号
	 * @param r
	 *            终点序号
	 */
	private static void _quick(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition(arr, p, r);
			_quick(arr, p, q - 1);
			_quick(arr, q + 1, r);
		}
	}

	private static int partition(int[] arr, int p, int r) {
		// 快速排序分解阶段分解为四个部分:
		// 比x小的部分 : [p, i -1]
		// 比x大的部分 : [i, j - 1]
		// 未处理的部分: [j, r -1]
		// x部分:x
		int x = arr[r];
		int i = p; // i指比x大的部分的第一个元素
		for (int j = p; j < r; j++) {
			if (arr[j] <= x) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, r);
		return i;
	}

	private static void swap(int[] arr, int i, int j) {
		if (i == j) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * 归并
	 * 
	 * @param arr
	 */
	public static void merge(int[] arr) {
		merge(arr, 0, arr.length - 1);
	}

	/**
	 * 
	 * @param arr
	 * @param start
	 *            开始下标
	 * @param end
	 *            结束下标
	 */
	public static void merge(int[] arr, int start, int end) {
		if (start < end) {
			int half = (start + end) / 2;
			merge(arr, start, half);
			merge(arr, half + 1, end);
			_merge(arr, start, half, end);
		}

	}

	public static void heapSort(int[] arr) {
		buildHeap(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			int max = arr[0];
			arr[0] = arr[i];
			arr[i] = max;
			maxHeapFy(arr, 0, i);
		}
	}

    /**
     * 建堆,从非叶子节点开始维护堆的性质
     * @param arr
     */
	private static void buildHeap(int[] arr) {
		int m = arr.length / 2;
		for (int i = m - 1; i >= 0; i--) {
			maxHeapFy(arr, i, arr.length);
		}

	}

    /**
     * 维护堆的性质
     * @param arr
     * @param targetIndex
     * @param heapSize
     */
	private static void maxHeapFy(int[] arr, int targetIndex, int heapSize) {
		int target = arr[targetIndex];
		int idxLeft = (targetIndex << 1) + 1;
		int idxRight = (targetIndex + 1) << 1;

		int larger;
		if (idxLeft < heapSize && arr[idxLeft] > target) {
			larger = idxLeft;
		} else {
			larger = targetIndex;
		}
		if (idxRight < heapSize && arr[idxRight] > arr[larger]) {
			larger = idxRight;
		}
		if (larger != targetIndex) {
			int tmp = arr[targetIndex];
			arr[targetIndex] = arr[larger];
			arr[larger] = tmp;
			maxHeapFy(arr, larger, heapSize);
		}
	}

	static class QuickSorter {
		private int[] arr;

		public QuickSorter(int[] arr) {
			this.arr = arr;
		}

		private void recursiveSort(int low, int high) {
			if (low >= high) {
				return;
			}
			int _low = low, _high = high;
			int key = arr[_low]; // 下标low的数字作为基准数字
			// 一趟排序,key左边的都比key小,key右边的都比key大
			while (_low < _high) {
				while (_low < _high && arr[_high] >= key) {
					_high--;
				}
				if (_low < _high) {
					swap(_low, _high);
					_low++;
				}
				while (_low < _high && arr[_low] <= key) {
					_low++;
				}
				if (_low < _high) {
					swap(_low, _high);
					_high--;
				}
			}
			recursiveSort(low, _low - 1);
			recursiveSort(_high + 1, high);

		}

		public void sort() {
			recursiveSort(0, arr.length - 1);
		}

		private void swap(int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}

	}

	static class MergeSorter {
		private int[] arr;

		public MergeSorter(int[] arr) {
			this.arr = arr;
		}

		private void mergeTwoArray(int low, int high, int mid) {
			int[] temp = new int[high - low + 1];
			int i = low, j = mid + 1, k = 0;
			while (i <= mid && j <= high) {
				if (arr[i] < arr[j]) {
					temp[k++] = arr[i++];
				} else {
					temp[k++] = arr[j++];
				}
			}
			while (i <= mid) {
				temp[k++] = arr[i++];
			}
			while (j <= high) {
				temp[k++] = arr[j++];
			}
			for (int e : temp) {
				arr[low++] = e;
			}
		}

		private void recursiveSort(int low, int high) {
			if (low < high) {
				int mid = (low + high) / 2;
				recursiveSort(low, mid);
				recursiveSort(mid + 1, high);
				mergeTwoArray(low, high, mid);
			}
		}

		public void sort() {
			recursiveSort(0, arr.length - 1);
		}
	}

	private static void _merge(int[] arr, int p, int q, int r) {
		int ll = q - p + 1;
		int lr = r - q;
		int[] larr = new int[ll + 1];
		int[] rarr = new int[lr + 1];
		for (int i = 0; i < ll; i++) {
			larr[i] = arr[i + p];
		}
		for (int i = 0; i < lr; i++) {
			rarr[i] = arr[i + q + 1];
		}
		larr[ll] = Integer.MAX_VALUE;
		rarr[lr] = Integer.MAX_VALUE;
		int lpoint = 0;
		int rpoint = 0;
		for (int i = 0; i < r - p + 1; i++) {
			int le = larr[lpoint];
			int re = rarr[rpoint];
			if (le <= re) {
				arr[p + i] = le;
				lpoint++;
			} else {
				arr[p + i] = re;
				rpoint++;
			}
		}

	}

}
