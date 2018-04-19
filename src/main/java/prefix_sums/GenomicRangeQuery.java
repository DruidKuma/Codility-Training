package prefix_sums;

import java.util.Arrays;

/**
 * A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence.
 * Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively.
 * You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
 *
 * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters.
 * There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers.
 * The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
 *
 * For example, consider string S = CAGCCTA and arrays P, Q such that:
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 *
 * The answers to these M = 3 queries are as follows:
 * - The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
 * - The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
 * - The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
 *
 * Write a function:
 *
 * class Solution { public int[] solution(String S, int[] P, int[] Q); }
 *
 * that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.
 *
 * The sequence should be returned as:
 * - a Results structure (in C), or
 * - a vector of integers (in C++), or
 * - a Results record (in Pascal), or
 * - an array of integers (in any other programming language).
 *
 * For example, given the string S = CAGCCTA and arrays P, Q such that:
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 * the function should return the values [2, 4, 1], as explained above.
 *
 * Assume that:
 * - N is an integer within the range [1..100,000];
 * - M is an integer within the range [1..50,000];
 * - each element of arrays P, Q is an integer within the range [0..N − 1];
 * - P[K] ≤ Q[K], where 0 ≤ K < M;
 * - string S consists only of upper-case English letters A, C, G, T.
 *
 * Complexity:
 * - expected worst-case time complexity is O(N+M);
 * - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class GenomicRangeQuery {

    public void updatePrefixSumForNucleotide(int[] prefixSums, int index, char nucleotide, char destNucleotide) {
        prefixSums[index] = (index > 0 ? prefixSums[index - 1] : 0) + (nucleotide == destNucleotide ? 1 : 0);
    }

    public boolean isfNumChangedOnRange(int[] prefixSums, int start, int end) {
        //if we check from the start, just check if by the end at least one nucleotide is present
        if (start == 0) return prefixSums[end] > 0;
        //start checking from one step earlier (in case target nucleotide is on the start index)
        return prefixSums[end] - prefixSums[start - 1] > 0;
    }

    public int[] solution(String S, int[] P, int[] Q) {
        //preprocess (prefix sums for each nucleotide type individually)
        int[] a = new int[S.length()];
        int[] c = new int[S.length()];
        int[] g = new int[S.length()];
        for (int indx = 0; indx < S.length(); indx++) {
            char nucleotide = S.charAt(indx);
            updatePrefixSumForNucleotide(a, indx, nucleotide, 'A');
            updatePrefixSumForNucleotide(c, indx, nucleotide, 'C');
            updatePrefixSumForNucleotide(g, indx, nucleotide, 'G');
        }

        //process result
        int[] result = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            int start = P[i];
            int end = Q[i];

            //check from min nucleotide ('A')
            //if number changed on the range, nucleotide is present (and has minimal value)
            if(isfNumChangedOnRange(a, start, end)) result[i] = 1;
            else if (isfNumChangedOnRange(c, start, end)) result[i] = 2;
            else if (isfNumChangedOnRange(g, start, end)) result[i] = 3;
            else result[i] = 4;
        }
        return result;
    }

    public static void main(String[] args) {
        GenomicRangeQuery solution = new GenomicRangeQuery();

        System.out.println(Arrays.toString(solution.solution("CAGCCTA", new int[] {2, 5, 0}, new int[] {4, 5, 6}))); //should return [2, 4, 1]
        System.out.println(Arrays.toString(solution.solution("A", new int[] {0}, new int[] {0}))); //should return [1]
        System.out.println(Arrays.toString(solution.solution("AC", new int[] {0, 0, 1}, new int[] {0, 1, 1}))); //should return [1, 1, 2]
    }
}
