package sorting;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Write a function
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.
 *
 * Assume that:
 * - N is an integer within the range [0..100,000];
 * - each element of array A is an integer within the range [−1,000,000..1,000,000].
 *
 * For example, given array A consisting of six elements such that:
 * A[0] = 2    A[1] = 1    A[2] = 1
 * A[3] = 2    A[4] = 3    A[5] = 1
 * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
 *
 * Complexity:
 * - expected worst-case time complexity is O(N*log(N));
 * - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class Distinct {

    public int solution(int[] A) {
        //with streams Codility gives 91%, failing on performance test (distinct() method is inefficient)
        //return (int) IntStream.of(A).distinct().count();
        Set<Integer> unique = new HashSet<>();
        for (int element : A) {
            unique.add(element);
        }
        return unique.size();
    }

    public static void main(String[] args) {
        Distinct solution = new Distinct();

        System.out.println(solution.solution(new int[] {2, 1, 1, 2, 3, 1})); // should return 3
    }
}
