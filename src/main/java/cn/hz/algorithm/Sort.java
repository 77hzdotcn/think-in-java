package cn.hz.algorithm;

/**
 * 排序算法
 * 
 * @author wangxf
 * @date 2016年6月28日
 */
public class Sort {

	/**
	 * 冒泡
	 * 
	 * @param arr
	 */
	public static void bubble(int[] arr) {
		for (int i = 0, l = arr.length - 1; i < l; i++) {
			for (int j = 0; j < l - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 快速
	 * 
	 * @param arr
	 */
	public static void quick(int[] arr) {
		new QuickSorter(arr).sort();
	}

	/**
	 * 归并
	 * 
	 * @param arr
	 */
	public static void merge(int[] arr) {
		new QuickSorter(arr).sort();
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

}
