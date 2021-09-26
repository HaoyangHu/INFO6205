/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for (int i = from + 1; i < to; i++) {
            int j = i - 1;
            while(j >= from && helper.compare(xs, j, j + 1) > 0){
                helper.swap(xs, j,j + 1);
                j--;
            }
        }

        // TO BE IMPLEMENTED
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    private static Integer[] generateRandomArray(int n){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++){
            arr[i] = new Integer((int)(Math.random() * n));
        }
        return arr;
    }

    private static Integer[] generateOrderedArray(int n){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }
        return arr;
    }

    private static Integer[] generatePartiallyOrderedArray(int n){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n / 2; i++) {
            arr[i] = new Integer(i);
        }
        for (int j = n / 2; j < n; j++){
            arr[j] = new Integer((int)(Math.random() * n));
        }
        return arr;
    }

    private static Integer[] generateReverseOrderedArray(int n){
        Integer[] arr = new Integer[n];
        for (int i = n - 1 ; i >= 0; i--) {
            arr[n - i - 1] = new Integer(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            int n = (int)(Math.pow(2,i)*100);
            Integer[] orderArr = generateOrderedArray(n);
            GenericSort<Integer> sorter = new InsertionSort<>();
            Benchmark_Timer bm = new Benchmark_Timer(" ordered array time", b->sorter.sort(orderArr));
            System.out.println(bm.run(true, 100));
        }
        System.out.println("---------------------------------------------------------------------------------");

        for (int i = 1; i <= 8; i++) {
            int n = (int)(Math.pow(2,i)*100);
            Integer[] randomArr = generateRandomArray(n);
            GenericSort<Integer> sorter = new InsertionSort<>();
            Benchmark_Timer bm = new Benchmark_Timer(" random array time", b->sorter.sort(randomArr));
            System.out.println(bm.run(true, 100));
        }
        System.out.println("---------------------------------------------------------------------------------");

        for (int i = 1; i <= 8; i++) {
            int n = (int)(Math.pow(2,i)*100);
            Integer[] partArr = generatePartiallyOrderedArray(n);
            GenericSort<Integer> sorter = new InsertionSort<>();
            Benchmark_Timer bm = new Benchmark_Timer(" partially ordered array time", b->sorter.sort(partArr));
            System.out.println(bm.run(true, 100));
        }
        System.out.println("---------------------------------------------------------------------------------");

        for (int i = 1; i <= 8; i++) {
            int n = (int)(Math.pow(2,i)*100);
            Integer[] reverseArr = generateReverseOrderedArray(n);
            GenericSort<Integer> sorter = new InsertionSort<>();
            Benchmark_Timer bm = new Benchmark_Timer(" reversed array time", b->sorter.sort(reverseArr));
            System.out.println(bm.run(true, 100));
        }
    }
}
