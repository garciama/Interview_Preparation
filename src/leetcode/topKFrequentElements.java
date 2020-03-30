package leetcode;

/*
LeetCode 347. Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
import java.util.*;

public class topKFrequentElements {

    /*
    The solution to this problem involves first counting the number of occurrences for each element, then
    putting each element into a bucket that represents its number of occurrences. For simplicity, we will have
    n+1 buckets, where n = number of elements in nums, so we buckets[5] represents all elements that have a frequency
    of 5.

    We will put all elements in their corresponding bucket, then starting at the highest bucket, we will remove
    k elements and put them into a list that is returned.

    Time: O(n) where n = number of elements in nums.
    Space: O(n) where n = number of elements in nums because we are storing frequencies and using buckets.
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        List<Integer>[] buckets = new List[nums.length + 1];
        List<Integer> results = new ArrayList<>();

        // Count the frequencies of each element:
        for(int num: nums){
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }

        // Put the elements in their correct bucket:
        for(int key: frequencies.keySet()){
            int frequency = frequencies.get(key);
            if(buckets[frequency] == null)
                buckets[frequency] = new ArrayList<>();
            buckets[frequency].add(key);
        }

        // Retrieving the top k frequent elements:
        for(int i = buckets.length -1; i >= 0 && results.size() < k; i--){
            if(buckets[i] != null){
                List<Integer> currentBucket = buckets[i];

                for(int j = 0; j < currentBucket.size() && results.size() < k; j++){
                    results.add(currentBucket.get(j));
                }
            }
        }

        return results;
    }
}
