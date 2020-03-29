package leetcode;

/*
LeetCode 647. Palindromic Substrings

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".


Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Note:
The input string length won't exceed 1000.
 */

/*
Takeaways:
- Remember this technique for identifying palindromes: starting from either the middle index or two middle indices,
  going outward the chars at middle +1 and middle -1 and so on should be the same.
 */

public class palindromicSubstrings {

    /*
    Brute force solution: Check all substrings. This is O(n^2) because there are n(n+1)/2 substrings possible.

    Since we know that single characters are substrings, we know there at least s.length() palindromes.

    Another possible solution: check at each index for the total number of palindromes with the current index as
    the middle point, and as the current index and next index as two middle points.

    Solution in end: Idea is start from each index and try to extend palindrome for both odd and even length.

    Time: O(n^2)
    Space: O(1) because we are just using the given string s, and we are always using the same variables to count
           the number of substrings.
     */
    public int countSubstrings(String s) {
        int totalPalindromes = 0;

        for(int i = 0; i < s.length(); i++){
            totalPalindromes += checkPalindromes(s, i, i);
            if(i+1 < s.length())
                totalPalindromes += checkPalindromes(s, i, i+1);
        }

        return totalPalindromes;
    }

    private int checkPalindromes(String s, int index1, int index2){
        int palindromesFound = 0;

        for(int i = index1, j = index2; i >= 0 && j < s.length(); i--, j++){
            if(s.charAt(i) == s.charAt(j))
                palindromesFound++;
            else
                break;
        }

        return palindromesFound;
    }
}
