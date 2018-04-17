package iterations;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 *
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2.
 * The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3.
 * The number 20 has binary representation 10100 and contains one binary gap of length 1.
 * The number 15 has binary representation 1111 and has no binary gaps.
 *
 * Write a function:
 *
 * class Solution { public int solution(int N); }
 *
 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
 *
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5.
 *
 * Assume that:
 * N is an integer within the range [1..2,147,483,647].
 *
 * Complexity:
 * expected worst-case time complexity is O(log(N));
 * expected worst-case space complexity is O(1).
 */
public class BinaryGap {

    public int solution(int N) {
        //convert to binary representation
        String binary = Integer.toBinaryString(N);

        int result = 0;

        int index = 0;
        while(index < binary.length()) {
            // get next start/stop indexes of '0' sequence
            int start = binary.indexOf('0', index);
            int end = binary.indexOf('1', start);

            // if we either get to the end of the sequences OR last sequence doesn't end with '1'
            if(start < 0 || end < 0) break;

            // calculate sequence length and update max found length so far
            int currentSize = end - start;
            if(currentSize > result) result = currentSize;

            // update index to check further
            index = end + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryGap solution = new BinaryGap();

        System.out.println(solution.solution(9)); // should return: 2
        System.out.println(solution.solution(529)); // should return: 4
        System.out.println(solution.solution(20)); // should return: 1
        System.out.println(solution.solution(15)); // should return: 0
        System.out.println(solution.solution(1041)); // should return: 5
    }
}
