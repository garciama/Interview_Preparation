package leetcode;

/*
    Leetcode 424. Longest Repeating Character Replacement

    Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

    In one operation, you can choose any character of the string and change it to any other uppercase English character.

    Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

    Note:
    Both the string's length and k will not exceed 10^4.

    Example 1:

    Input:
    s = "ABAB", k = 2

    Output:
    4

    Explanation:
    Replace the two 'A's with two 'B's or vice versa.


    Example 2:

    Input:
    s = "AABABBA", k = 1

    Output:
    4

    Explanation:
    Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class longestRepCharReplacement {

    public int characterReplacement(String s, int k) {
        if(s.length() == 0)
            return 0;
        if(k >= s.length())
            return s.length();

        int longestLength = 0;
        // Keeps track of the current window's char counts.
        int[] windowCharCounts = new int[26];
        // Max in the current window.
        int windowMax = 0;
        int start = 0;

        // Loop through all characters of s, but only looking through a valid window where we can replace the number
        // of chars and k >= 0.
        for(int end = 0; end < s.length(); end++){
            // First update the char count of the char at the end of the current valid window:
            windowCharCounts[s.charAt(end) - 'A']++;

            // Check if there is a new windowMax for the current window of characters:
            windowMax = Math.max(windowMax,windowCharCounts[s.charAt(end) - 'A']);

            // Shrink window if invalid:
            while( (end - start + 1 - windowMax) > k){
                // Shrink window by reducing charCount of character as "start" position by one then start++
                windowCharCounts[s.charAt(start) - 'A']--;
                start++;
            }

            // Check for new longestLength:
            longestLength = Math.max(end - start + 1, longestLength);
        }

        return longestLength;
    }
}
