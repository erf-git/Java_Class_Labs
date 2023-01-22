package sort.stu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Perform an out of place merge sort on an array of integers.
 *
 * merge_sort (not in place):
 *     best=O(nlogn)
 *     worst=O(nlogn)
 *
 * @author RIT CS
 */
public class MergeSort {
    /**
     * Split the array list on the left side.
     *
     * @param data the full array of data
     * @return the left half side of the data
     */
    private static ArrayList<Integer> splitLeft(ArrayList<Integer> data) {

        ArrayList<Integer> leftList = new ArrayList<>();

        for (int i = 0; i < data.size(); i += 2) {
            leftList.add(data.get(i));
        }

        return leftList;
    }

    /**
     * Split the array list on the right side.
     *
     * @param data the full array of data
     * @return the right half side of the data
     */
    private static ArrayList<Integer> splitRight(ArrayList<Integer> data) {

        ArrayList<Integer> rightList = new ArrayList<>();

        for (int i = 1; i < data.size(); i += 2) {
            rightList.add(data.get(i));
        }

        return rightList;
    }

    /**
     * Merges two sorted array lists, left and right, into a combined sorted array list.
     *
     * @param left  a sorted array
     * @param right a sorted array
     * @return one combined sorted array
     */
    private static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {

        // the sorted left + right will be stored in result
        ArrayList<Integer> result = new ArrayList<>();
        int left_index = 0;
        int right_index = 0;

        // loop through until either the left or right list is exhausted
        while (left_index < left.size() && right_index < right.size()) {
            if (left.get(left_index) <= right.get(right_index)) {
                result.add(left.get(left_index));
                left_index++;
            } else {
                result.add(right.get(right_index));
                right_index++;
            }
        }

        // take the un-exhausted list and extend the remainder onto the result
        while (left_index < left.size()) {
            result.add(left.get(left_index));
            left_index++;
        }
        while (right_index < right.size()) {
            result.add(right.get(right_index));
            right_index++;
        }

        return result;
    }

    /**
     * Performs a merge sort and returns a newly sorted array list.
     *
     * @param data the data to be sorted
     * @return a sorted array
     */
    private static ArrayList<Integer> mergeSort(ArrayList<Integer> data) {

        // an empty list, or list of 1 element is already sorted
        if (data.size() < 2) {
            return data;
        } else {
            // split the data into left and right halves
            ArrayList<Integer> left = splitLeft(data);
            ArrayList<Integer> right = splitRight(data);
            // return the merged recursive merge_sort of the halves
            return merge(mergeSort(left), mergeSort(right));
        }

        //return null;
    }

    /**
     * Test function for mergeSort.
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
            List<Integer> sortData = Arrays.stream(data)
                                           .boxed()
                                           .collect(Collectors.toList());
            List<Integer> sorted = Arrays.stream(data)
                                         .boxed()
                                         .collect(Collectors.toList());
            // merge sort is not in place and returns a new sorted list
            sortData = mergeSort((ArrayList<Integer>) sortData);
            // use built in sort to compare against
            Collections.sort(sorted);
            // show the results of the comparison
            System.out.print("mergeSort: " + Arrays.stream(data)
                                                   .boxed()
                                                   .collect(Collectors.toList())
                             + " result: " + sortData);
            System.out.println(sortData.equals(sorted) ? " OK" : " FAIL");
        }
    }
}
