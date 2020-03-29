package leetcode;

/*
    Leetcode #153. Find Minimum in Rotated Sorted Array.

    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

    Find the minimum element.

    You may assume no duplicate exists in the array.

    Example 1:
    Input: [3,4,5,1,2]
    Output: 1

    Example 2:
    Input: [4,5,6,7,0,1,2]
    Output: 0
 */
class MinInSortedArray {
    public int findMin(int[] nums) {

        // Binary search method:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            // If at inflection point, return number at mid.
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[start] <= nums[mid] && nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return nums[start];


        /*
        // Using two pointers:
        int min = Integer.MAX_VALUE;
        int leftMin = nums[0];
        int rightMin = nums[nums.length -1];

        // First checking if the given array isn't shifted, if so return the 0th element.
        if(leftMin < rightMin)
            return leftMin;

        // If the given array is shifted, start from the right and find the minimum value.
        // Do this by comparing the i^th element to the i-1^th element. If i^th element < i-1^th element,
        // then we have found the minimum, return element at index i.
        int i = nums.length - 2;
        while(i > 0){
            int current = nums[i];

            if(current < rightMin)
                rightMin = current;
            else
                return rightMin;
            i--;
        }

        return rightMin;
        */
    }
}
