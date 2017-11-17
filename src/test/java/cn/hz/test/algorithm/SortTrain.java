package cn.hz.test.algorithm;

import java.util.Random;

/**
 * @Author wangxf
 * @Date 2017/11/12
 */
public class SortTrain {

    public static void main(String[] args){
        int size = 5;
        int[] numArray = new int[size];
        for(int i = 0; i < size; i++){
            numArray[i] = new Random().nextInt(50);
        }
        System.out.print("source : ");
        print(numArray);
        //sort
        heap(numArray);
        System.out.print("result : ");
        print(numArray);
    }

    public static void print(int[] numArray){
        for(int i : numArray){
            System.out.print(i + " ");
        }
        System.out.println();

    }

    public static void bubble(int[] arr){
        for(int i = 0, l = arr.length; i < l - 1; i++){       //循环遍历,每次找出当前最大;l个数需要 l - 1次遍历
            for(int j = 0; j < l - i - 1; j++){     //已经找出来的最大值就不用比较了
                int a = arr[j];
                int b = arr[j + 1];
                if(a > b){
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void insert(int[] arr){
        for(int i = 1, l = arr.length; i < l; i++){
            int key = arr[i];
            int j = i - 1;
            for(; j >= 0; j--){
                if(arr[j] > key){
                    arr[j + 1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    public static void heap(int[] arr){
        build_max_heap(arr);
        for(int i = arr.length - 1; i >1; i--){
            swap(arr, 0, i);
            max_heapfy(arr, 0, i);
        }
    }

    private static void max_heapfy(int[] arr, int i, int size) {
        int left = (i << 1) + 1;
        int right = (i << 1) + 2;

        int largest = i;
        if(left < size && arr[left] > arr[i]){
            largest = left;
        }
        if(right < size && arr[right] > arr[i]){
            largest = right;
        }
        if(largest != i){
            swap(arr, largest, i);
            max_heapfy(arr, largest, size);
        }
    }

    private static void build_max_heap(int[] arr) {
        int m = arr.length / 2;
        for(int i = m - 1; i >= 0; i--){
            max_heapfy(arr, i, arr.length);
        }
    }

    public static void quick(int[] arr){

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
