package com.shuframework.arithmetic;

import com.shuframework.arithmetic.sorts.BubbleSort;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SortUtilTest {


//    @Before
//    public void init() {
//    }
    int[] intArr = {3, 2, 1, 6, 4, 5, 7};
//    int[] intArr = {7, 6, 5, 4, 3, 2, 1};


    @Test
    public void bubbleSort1() {
        BubbleSort.sort(intArr);
        System.out.println(Arrays.toString(intArr));
    }
    @Test
    public void bubbleSort2() {
        BubbleSort.sortHasFlag(intArr);
        System.out.println(Arrays.toString(intArr));
    }
    @Test
    public void bubbleSort3() {
        BubbleSort.sortClassic(intArr);
        System.out.println(Arrays.toString(intArr));
    }


//    @Test
//    public void bubbleSort() {
////        int[] intArr = {3, 2, 1, 6, 4, 5, 7};
//        int[] intArr = {7, 6, 5, 4, 3, 2, 1};
//        SortUtil.bubbleSort(intArr);
//        System.out.println(Arrays.toString(intArr));
//    }
//
//    @Test
//    public void selectionSort() {
////        int[] intArr = {3, 2, 1, 6, 4, 5, 7};
//        int[] intArr = {7, 6, 5, 4, 3, 2, 1};
//        SortUtil.selectionSort(intArr);
//        System.out.println(Arrays.toString(intArr));
//    }
//
//    @Test
//    public void insertSort() {
//        int[] intArr = {3, 2, 1, 6, 4, 5, 7};
////        int[] intArr = {7, 6, 5, 4, 3, 2, 1};
//        SortUtil.insertSort(intArr);
//        System.out.println(Arrays.toString(intArr));
//    }
}