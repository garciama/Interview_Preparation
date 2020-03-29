package leetcode;

/*
Leetcode 19. Remove Nth Node From End of List

Given a linked list, remove the n-th node from the end of list and return its head.

Example:
Given linked list: 1->2->3->4->5->null, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5->null.

Note:
Given n will always be valid.

Follow up:
Could you do this in one pass?
 */



public class removeNthFromEnd {

    /*
    The main idea for this function is to have two pointers that are n+1 spaces apart. You will then move those pointers
    up 1 by 1 until the further ahead pointer's .next is null.
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode walker = newHead;
        ListNode runner = newHead;
        while(n>0){
            runner = runner.next;
            n--;
        }
        while(runner.next!=null){
            runner = runner.next;
            walker=walker.next;
        }
        walker.next = walker.next.next;
        return newHead.next;
    }


    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
