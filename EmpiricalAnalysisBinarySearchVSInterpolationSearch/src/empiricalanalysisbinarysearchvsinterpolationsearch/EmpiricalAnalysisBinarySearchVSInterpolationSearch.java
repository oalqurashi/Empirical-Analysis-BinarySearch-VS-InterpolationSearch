//Name: Omar Abdulziz Alqurashi, 1742589, Section: DB
package empiricalanalysisbinarysearchvsinterpolationsearch;

import java.util.Arrays;

/**
 *
 * @author omar-
 */
public class EmpiricalAnalysisBinarySearchVSInterpolationSearch {

    public static void main(String[] args) {
        long startTime, endTime, totalTimeB, bestCaseB, worstCaseB,
                totalTimeI, bestCaseI, worstCaseI, totalTime;
        int keyValue;
        for (int n = 0; n <= 500000; n = n + 100000) {
            int array[] = new int[n]; // size = n
            System.out.println("For n = " + (n) + ":\n"
                    + "  Binary Search       |   Interpolation Search");
            // initialization of the following:
            totalTimeB = 0;
            bestCaseB = 2000000000;
            worstCaseB = -1;
            totalTimeI = 0;
            bestCaseI = 2000000000;
            worstCaseI = -1;
            for (int i = 0; i < n; i++) {
                array[i] = (int) (Math.random() * 1001);
            }
            for (int t = 0; t < 3; t++) { // trials
                keyValue = (int) (Math.random() * 2001);
                Arrays.sort(array);//sort the array
                startTime = System.nanoTime();
                binarySearch(array, keyValue);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                System.out.printf("  Total Time: %6d  |   ",
                         totalTime);//nanoSecond
                totalTimeB += totalTime;
                if (totalTime < bestCaseB) {
                    bestCaseB = totalTime;
                }
                if (totalTime > worstCaseB) {
                    worstCaseB = totalTime;
                }

                startTime = System.nanoTime();
                InterpolationSearch(array, keyValue);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                System.out.printf("Total Time: %6d (Trial: "
                        + (t + 1) + ")\n", totalTime);//nanoSecond
                totalTimeI += totalTime;

                if (totalTime < bestCaseI) {
                    bestCaseI = totalTime;
                }
                if (totalTime > worstCaseI) {
                    worstCaseI = totalTime;
                }
            } // end of the inner "for loop"
            System.out.printf("  ----------------- Cases ------------------\n"
                    + "   Best case: %6d  |    Best case: %6d\n"
                    + "   Worst case:%6d  |    Worst case:%6d\n"
                    + "   AVG case:  %6.0f  |    AVG case:  %6.0f\n\n",
                    bestCaseB, bestCaseI, worstCaseB, worstCaseI, (double) totalTimeB / 3, (double) totalTimeI / 3);
        } // end of the outer "for loop"
    }

    public static int binarySearch(int A[], int k) {
        int l = 0; // Lower bound
        int r = A.length - 1; // Upper bound                
        while (l <= r) {
            int m = (l + r) / 2; // midpoint
            if (A[m] == k) {
                return m; // found
            } else if (A[m] > k) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;// if not found...
    } // end of binary search    

    public static int InterpolationSearch(int A[], int v) {
        int l = 0; // Lower bound
        int r = A.length - 1; // Upper bound                
        while (l <= r && v >= A[l] && v <= A[r]) {
            int x = l + ((v - A[l]) * (r - l) / (A[r] - A[l]));
            if (A[x] == v) {
                return x; // found
            } else if (A[x] > v) {
                r = x - 1;
            } else {
                l = x + 1;
            }
        }
        return -1;// if not found...
    } // end of Interpolation Search

}
