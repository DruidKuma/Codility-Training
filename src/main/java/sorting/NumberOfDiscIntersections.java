package sorting;

import java.util.Arrays;

/**
 * We draw N discs on a plane. The discs are numbered from 0 to N − 1.
 * An array A of N non-negative integers, specifying the radiuses of the discs, is given.
 * The J-th disc is drawn with its center at (J, 0) and radius A[J].
 *
 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).
 *
 * The figure below shows discs drawn for N = 6 and A as follows:
 *   A[0] = 1
 *   A[1] = 5
 *   A[2] = 2
 *   A[3] = 1
 *   A[4] = 4
 *   A[5] = 0
 *
 * There are eleven (unordered) pairs of discs that intersect, namely:
 * - discs 1 and 4 intersect, and both intersect with all the other discs;
 * - disc 2 also intersects with discs 0 and 3.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs.
 * The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
 *
 * Given array A shown above, the function should return 11, as explained above.
 *
 * Assume that:
 * - N is an integer within the range [0..100,000];
 * - each element of array A is an integer within the range [0..2,147,483,647].
 *
 * Complexity:
 * - expected worst-case time complexity is O(N*log(N));
 * - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class NumberOfDiscIntersections {

    public int solution(int[] A) {
        //aggregate and sort starts of the circles (min left points)
        long[] starts = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            starts[i] = i - A[i];
        }
        Arrays.sort(starts);

        int pairs = 0;
        for (int i = 0; i < A.length; i++) {
            //idea is to find number of disks that have started before current disk ends
            //we also prevent double counting by subtracting circles we have seen so far (and also the current one)
            pairs += bisect(starts, i + ((long)A[i])) - (i + 1);
            if(pairs > 10_000_000) return -1;
        }
        return pairs;
    }

    //performs bisection search and return index in sorted array where to insert given element
    public int bisect(long[] numbers, long element) {
        int lower = 0;
        int upper = numbers.length;
        while(lower < upper) {
            int middle = (lower + upper) / 2;
            if(element < numbers[middle]) upper = middle;
            else lower = middle + 1;
        }
        return lower;
    }

    public static void main(String[] args) {
        NumberOfDiscIntersections solution = new NumberOfDiscIntersections();

        System.out.println(solution.solution(new int[] {1, 5, 2, 1, 4, 0})); //should return 11
        System.out.println(solution.solution(new int[] {1, 2147483647, 0})); //should return 2
    }
}
