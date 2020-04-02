package leetcode.thirtyDayChallenge;
import java.util.*;

/*
Day 1:

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4
 */
public class singleNumber {

    public int singleNumber(int[] nums) {
        // Can use a HashSet to add then remove the values, at the end the
        // only value that should be on the HashSet should be the value that appears once.
        HashSet<Integer> values = new HashSet<>();

        for (int num : nums) {
            if(values.contains(num))
                values.remove(num);
            else
                values.add(num);
        }

        List<Integer> convertedList = new ArrayList<>(values);
        return convertedList.get(0);
    }

}
