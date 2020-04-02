package leetcode.thirtyDayChallenge;
import java.net.Inet4Address;
import java.util.*;

/*
Day 1:

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number
by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it
loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
 */

public class happyNumber {

    /*
    The main thing to figure out with this problem is how do you find when the loop is endlessly in a cycle.
     */
    public boolean isHappy(int n) {
        // Use a HashSet to keep track of which sums have been calculated so far. We are in an endless loop
        // if we calculate a value we have seen in the past again.
        HashSet<Integer> calculatedSums = new HashSet<>();

        while(!calculatedSums.contains(n)){
            calculatedSums.add(n);
            n = calculateSum(n);
            if(n == 1)
                return true;
        }

        return false;
    }

    private int calculateSum(int n){
        int newSum = 0;

        // To calculate the sum of the each individual number in n squared, continually square n%10 and then divide
        // n/10. We continue to do this until n = 0. This method is a good way to get each individual number in n.
        while(n>0){
            newSum+= Math.pow((n%10), 2);
            n = n/10;
        }
        return newSum;
    }
}
