package leetcode;

/*
Leetcode 3. Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

/*
Takeaways:
- Remember that you can use int[] charCounts = new int[256] for counting all types of characters.
- Remember how to use the sliding window technique.
 */

public class longestSubstrNoRepeats {

    /*
    I think the main idea behind this problem is to use the sliding window strategy.
    A valid window is one that has to repeating characters.
    We can check for repeating characters by having an integer array that keeps track of the character counts.
    We will need to keep track of the overallMax, the current window's max, start position, and end position of the
    window.
    We will have a for loop that goes through the characters of s, and at first increment the charCount of the next
    character in s.
        If that character count is > 1 then we have to shrink the window until it is 1.
        After shrinking the window, check if the new windowSize is > overallMax
    return overallMax
     */

    public int lengthOfLongestSubstring(String s) {
        int overallMax = 0;
        int start = 0;
        // Assuming the string contains only a-z lowercase.
        int charCounts[] = new int[256];

        for(int end = 0; end < s.length(); end++){
            char currentChar = s.charAt(end);
            charCounts[currentChar]++;

            while(charCounts[currentChar] > 1){
                charCounts[s.charAt(start)]--;
                start++;
            }

            overallMax = Math.max(end-start+1, overallMax);
        }

        return overallMax;

        // Time: O(n) where n = length of s.
        // Space: O(k) where k = length of sliding window.
    }
}
