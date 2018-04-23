package leader;

import java.util.Arrays;
import java.util.Stack;

/**
 * An array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.
 *
 * For example, consider array A such that
 * A[0] = 3    A[1] = 4    A[2] =  3
 * A[3] = 2    A[4] = 3    A[5] = -1
 * A[6] = 3    A[7] = 3
 * The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.
 *
 * Write a function
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.
 *
 * Assume that:
 * - N is an integer within the range [0..100,000];
 * - each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 *
 * For example, given array A such that
 * A[0] = 3    A[1] = 4    A[2] =  3
 * A[3] = 2    A[4] = 3    A[5] = -1
 * A[6] = 3    A[7] = 3
 * the function may return 0, 2, 4, 6 or 7, as explained above.
 *
 * Complexity:
 * - expected worst-case time complexity is O(N);
 * - expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 */
public class Dominator {

    public int solution(int[] A) {
        //corner case
        if(A.length < 1) return -1;

        //look for leader candidate
        int size = 0;
        int value = -1;
        int indx = -1;
        for (int k = 0; k < A.length; k++) {
            if(size == 0) {
                size++;
                value = A[k];
                indx = k;
            }
            else size += value != A[k] ? -1 : 1;
        }

        //no candidate for leader
        if(size < 1) return -1;

        //count occurrences of the candidate
        int count = 0;
        for (int i : A) if(i == value) count++;

        return count > A.length / 2 ? indx : -1;
    }

    public static void main(String[] args) {
        Dominator solution = new Dominator();

        System.out.println(solution.solution(new int[] {3, 4, 3, 2, 3, -1, 3, 3})); //may return 0, 2, 4, 6 or 7
        System.out.println(solution.solution(new int[] {1, 2, 1})); //may return 0 or 2
        System.out.println(solution.solution(new int[] {0, 0, 1, 1, 1})); //may return 2, 3 or 4
    }
}
