package counting_elements;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *This is a demo task.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 *
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 * Given A = [1, 2, 3], the function should return 4.
 * Given A = [−1, −3], the function should return 1.
 *
 * Assume that:
 * - N is an integer within the range [1..100,000];
 * - each element of array A is an integer within the range [−1,000,000..1,000,000].
 *
 * Complexity:
 * - expected worst-case time complexity is O(N);
 * - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class MissingInteger {

    public int solution(int[] A) {
        // Codility gives 88% with streams, failing on performance test
        //Set<Integer> uniqueNumbers = IntStream.of(A).boxed().collect(Collectors.toSet());
        Set<Integer> uniqueNumbers = new HashSet<>();
        for(int num : A) {
            uniqueNumbers.add(num);
        }
        for (int i = 1; ; i++) {
            if(uniqueNumbers.contains(i)) continue;
            return i;
        }
    }

    public static void main(String[] args) {
        MissingInteger solution = new MissingInteger();

        System.out.println(solution.solution(new int[] {1, 2, 3})); // should return 4
        System.out.println(solution.solution(new int[] {-1, -3})); // should return 1
    }
}
