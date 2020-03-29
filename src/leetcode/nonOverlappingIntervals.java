package leetcode;

/*
LeetCode 435. Non-overlapping Intervals

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: [[1,2], [1,3], [2,3], [3,4]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

Takeaways:
- This is like the classic greedy problem: interval scheduling https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization
- Sorting Interval.end in ascending order is O(nlogn), then traverse intervals array to get
  the maximum number of non-overlapping intervals is O(n). Total is O(nlogn).
- Sorting by start or end doesn't matter. They both work.
- Sorting syntax with Java8+
    - Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        - uses lambda function.
 */
import java.util.*;

public class nonOverlappingIntervals {

    /*
    The solution for this one involves sorting the intervals by end time and going through the intervals,
    keeping track of which ones have to be removed.

    We start with the first interval and keep track of the endTime. If the next interval's start
    time is < our prevEndTime then we have to skip this interval. If not, we can use this interval
    and we update our prevEndTime to be the current interval's end time. At the end of this loop
    we should have the minimum number of intervals that need to be removed.

    Time: O(n * log n) where n = number of intervals, because we sort the array first.
    Space: O(1) because we only keep track of the minRemoved and prevEnd.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int minRemoved = 0;

        if(intervals.length == 0)
            return minRemoved;

        // Sort the array:
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[1], b[1]));

        int prevEnd = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){
            // If invalid interval(start time < prevEndTime), add to minRemoved:
            if(intervals[i][0] < prevEnd){
                minRemoved++;
            } else{
                prevEnd = intervals[i][1];
            }
        }

        return minRemoved;
    }

}
