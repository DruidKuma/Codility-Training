package sorting;

import java.util.Arrays;

/**
 * An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
 * A[P] + A[Q] > A[R],
 * A[Q] + A[R] > A[P],
 * A[R] + A[P] > A[Q].
 *
 * For example, consider array A such that:
 * A[0] = 10    A[1] = 2    A[2] = 5
 * A[3] = 1     A[4] = 8    A[5] = 20
 * Triplet (0, 2, 4) is triangular.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
 *
 * For example, given array A such that:
 * A[0] = 10    A[1] = 2    A[2] = 5
 * A[3] = 1     A[4] = 8    A[5] = 20
 * the function should return 1, as explained above.
 *
 * Given array A such that:
 * A[0] = 10    A[1] = 50    A[2] = 5
 * A[3] = 1
 * the function should return 0.
 *
 * Assume that:
 * - N is an integer within the range [0..100,000];
 * - each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 *
 * Complexity:
 * - expected worst-case time complexity is O(N*log(N));
 * - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class Triangle {

    public int solution(int[] A) {
        //check for corner case
        if(A.length < 3) return 0;

        //after sorting we need to check only A[P] + A[Q] > A[R]
        Arrays.sort(A);

        for (int i = 0; i < A.length - 2; i++) {
            //cast to long not to exceed integer range
            if(((long)A[i]) + A[i+1] > A[i+2]) return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Triangle solution = new Triangle();

        System.out.println(solution.solution(new int[] {10, 2, 5, 1, 8, 20})); //should return 1
        System.out.println(solution.solution(new int[] {10, 50, 5, 1})); //should return 0
    }
}
