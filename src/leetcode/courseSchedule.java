package leetcode;

/*
LeetCode 207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.


Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5

Takeaways:
- Topological sort problem, use Kahn's algorithm
- Knowing source removal algorithm helps for this problem.
- Basically just counting the number of prerequisites for all classes, then adding classes with no prereqs to a queue.
  Remove classes from that queue and remove them as listed prereqs for other classes. If you end up adding and
  removing all classes from the queue then you can finish all classes.

 */
import java.util.*;

public class courseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // This question is basically a topological sorting problem. We can solve this with a source removal algorithm (Kahn's algorithm):
        int[] indegree = new int[numCourses];
        Queue<Integer> completedCourses = new LinkedList<Integer>();

        // Count how many prerequisites each course has:
        // int[course1][course2] prerequisites. To take course1 you have to take course2, so course1 has course2 as a prereq.
        for(int[] pair:prerequisites){
            indegree[pair[0]]++;
        }

        // Add all courses to the queue that do not have any prerequisites:
        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0){
                completedCourses.add(i);
            }
        }

        // Go through all the courses that can be finished because they have no prerequisites and find where they are a prereq for other courses:
        while(!completedCourses.isEmpty()){
            numCourses--;
            // Take the next course from the queue that doesn't have any prerequisites:
            int course = completedCourses.poll();
            for(int[] pair:prerequisites){
                // If the prerequisite of a course is the course that was just removed, lower the number of prerequisites for that course:
                if(pair[1]==course){
                    indegree[pair[0]]--;
                    // If this course no longer has any prerequisites, add it to the queue.
                    if(indegree[pair[0]]==0){
                        completedCourses.add(pair[0]);
                    }
                }
            }
        }

        // If we successfully remove all nodes from the graph, it is possible to finish all courses.
        return numCourses==0;

        // Time: O(n^2) where n = number of prerequisites.
        // Space: O(n)
    }
}
