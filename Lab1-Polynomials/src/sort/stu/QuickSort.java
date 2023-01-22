package sort.stu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Perform an out of place quick sort on an array of integers.
 * <p>
 * quicksort:
 * best=O(nlogn)
 * worst=O(n^2)
 *
 * @author RIT CS
 */
public class QuickSort {
    /**
     * Partition the array list for values less than the pivot.
     *
     * @param data the full array of data
     * @param pivot the pivot
     * @return data less than the pivot
     */
    private static ArrayList<Integer> partitionLess(ArrayList<Integer> data, int pivot) {

        ArrayList<Integer> lessList = new ArrayList<>();

        for (Integer n : data) {
            if (n < pivot) {
                lessList.add(n);
            }
        }

        return lessList;
    }

    /**
     * Partition the array list for values equal to the pivot.
     *
     * @param data the full array of data
     * @param pivot the pivot
     * @return data equal to the pivot
     */
    private static ArrayList<Integer> partitionEqual(ArrayList<Integer> data, int pivot) {

        ArrayList<Integer> equalList = new ArrayList<>();

        for (Integer n : data) {
            if (n == pivot) {
                equalList.add(n);
            }
        }

        return equalList;
    }

    /**
     * Partition the array list for values greater than the pivot.
     *
     * @param data the full array of data
     * @param pivot the pivot
     * @return data greater than  the pivot
     */
    private static ArrayList<Integer> partitionGreater(ArrayList<Integer> data, int pivot) {

        ArrayList<Integer> greaterList = new ArrayList<>();

        for (Integer n : data) {
            if (n > pivot) {
                greaterList.add(n);
            }
        }

        return greaterList;
    }

    /**
     * Performs a quick sort and returns a newly sorted array list.
     *
     * @param data the data to be sorted
     * @return a sorted array
     */
    public static ArrayList<Integer> quickSort(ArrayList<Integer> data) {

        ArrayList<Integer> result = new ArrayList<>();

        if (data.size() == 0) {
            return result;
        } else {
            int pivot = data.get(0);
            ArrayList<Integer> less = partitionLess(data, pivot);
            ArrayList<Integer> equal = partitionEqual(data, pivot);
            ArrayList<Integer> greater = partitionGreater(data, pivot);

            result.addAll(quickSort(less));
            result.addAll(equal);
            result.addAll(quickSort(greater));

            return result;
        }
    }

    /**
     * Test function for quickSort.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        int[][] DATA = {
                {},
                {0},
                {0, 1},
                {1, 0},
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0},
                {9, 5, 2, 4, 3, 8, 0, 4, 0, 9}
        };

        for (int[] data : DATA) {
            // create two lists of the data
            List<Integer> sortData = Arrays.stream(data).boxed().collect(Collectors.toList());
            List<Integer> sorted = Arrays.stream(data).boxed().collect(Collectors.toList());
            // quick sort returns a new sorted list
            sortData = quickSort((ArrayList<Integer>) sortData);
            // use built in sort to compare against
            Collections.sort(sorted);
            // show the results of the comparison
            System.out.print("quickSort: " + Arrays.stream(data).boxed().collect(Collectors.toList()) +
                    " result: " + sortData);
            System.out.println(sortData.equals(sorted) ? " OK" : " FAIL");
        }
    }
}
