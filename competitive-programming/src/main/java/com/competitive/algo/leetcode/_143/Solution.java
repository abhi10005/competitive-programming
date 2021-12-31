package com.competitive.algo.leetcode._143;

import java.util.Stack;

public class Solution {

    /*
    Explanation -
        1. Identify the middle node using fast-slow pointer approach
        2. Keep a "hold" on the middle node so that when we are re-ordering,
            we know when to stop
        3. Once mid node is identified, resume slow pointer and stack the remaining
            nodes up
        4. Start from the beginning of the linkedlist and assign next of the node
            to the node popped out of stack.

        Why stack: Because we require reverse ordering, i.e. LIFO
     */
    public void reorderList(ListNode head) {
        ListNode fast, slow, hold, curr;
        fast = head;
        slow = head;
        curr = head;
        hold = null;

        while(fast != null){
            if(fast.next != null){
                fast = fast.next.next;
            }else {
                fast = null;
            }
            hold = slow;
            slow = slow.next;
        }

        Stack<ListNode> stack = new Stack<>();
        while(slow != null){
            stack.push(slow);
            slow = slow.next;
        }

        while(curr != hold){
            ListNode currNext = curr.next;
            curr.next = stack.pop();
            curr = curr.next;
            curr.next = currNext;
            curr = currNext;
        }

        if(!stack.isEmpty()){
            curr.next = stack.pop();
            curr = curr.next;
        }
        curr.next = null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


