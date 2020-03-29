package leetcode;

/*
Leetcode 76. Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

*/

/*
Takeaways:
- Had to look up substring syntax: Str.substring(minStartIndex, minEndIndex + 1);
    - substring includes the startIndex but not the endIndex so u have to add one.
 */

import java.util.HashMap;

public class minWindowSubstr {

    /*
    I think the solution for this one involves using a sliding window.
    A valid window has all the characters of T in it.
    We will continue to expand the window until we find all characters that are in T.
    We will shrink the window by one, which should remove one of the chars from T from our current window.
        So we will need to continue to increase the window until we find that missing character.
    At the end of each loop if we have a valid window, we will check to see if the current window's size is
    our new minimum. If it is we will track it, using a minStartIndex and minEndIndex.
    At the end of the array we can return a substring of s using those two indices.
     */
    public static String minWindow(String s, String t) {
        // First make an integer array to hold the characters of T.
        //
        // Then go through S, finding characters that are in T and keeping track of the minimum window's
        // start and end position. At the end return the substring of those positions recorded.


        if(s == null || s.length() == 0 || t == null || t.length() == 0)
            return "";

        int[] tChars = new int[128];

        for(int i = 0; i < t.length(); i++){
            tChars[t.charAt(i) - 'A']++;
        }

        int tCharsRemaining = t.length();
        int minWindowStart = 0;
        int minWindowLength = Integer.MAX_VALUE;
        int start = 0;

        // Main logic of the problem to find all the substrings in S that contain T:
        for(int end = 0; end < s.length(); end++){
            char currentChar = s.charAt(end);

            if(tChars[currentChar - 'A'] > 0)
                tCharsRemaining--;

            tChars[currentChar - 'A']--;

            while(tCharsRemaining == 0){

                // If there is a new minimum window, set variables:
                if(end - start + 1 < minWindowLength){
                    minWindowLength = end - start + 1;
                    minWindowStart = start;
                }

                tChars[s.charAt(start) - 'A']++;

                if( tChars[s.charAt(start) - 'A'] > 0)
                    tCharsRemaining++;

                start++;
            }
        }

        if(minWindowLength == Integer.MAX_VALUE)
            return "";

        return s.substring(minWindowStart, minWindowStart + minWindowLength);
    }

    public static void main(String[] args){
        String t = "ABC";
        String s = "ADOBECODEBANC";

        System.out.println(minWindow(s, t));
    }

}
