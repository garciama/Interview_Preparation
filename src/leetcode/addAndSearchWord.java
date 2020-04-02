package leetcode;
/*
LeetCode 211. Add and Search Word - Data structure design

Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.
 */
import java.util.*;

public class addAndSearchWord {

    class WordDictionary {

        private List<String> savedWords;

        /** Initialize your data structure here. */
        public WordDictionary() {
            savedWords = new ArrayList<>();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            savedWords.add(word);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            int wordLength = word.length();

            for(String existingWord: savedWords){
                // If two words don't have same length continue to check other words in the data structure.
                if(wordLength != existingWord.length())
                    continue;
                for(int i = 0; i < word.length(); i++){
                    if(existingWord.charAt(i) != word.charAt(i) || existingWord.charAt(i) != '.')
                        break;
                    if( i == word.length() -1)
                        return true;
                }
            }

            return false;
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
