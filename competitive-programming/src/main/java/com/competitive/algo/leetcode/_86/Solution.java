package com.competitive.algo.leetcode._86;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws Exception {
    Solution solution = new Solution();

        try (BufferedReader inputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_86/test-input.txt"));
                BufferedReader outputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_86/test-output.txt"))) {
            String line1;
            while((line1 = inputBr.readLine()) != null){
                String line2 = inputBr.readLine();
                String[] inputs = line1.split(",");
                int[] inputArr = Stream.of(inputs).mapToInt(Integer::parseInt)
                        .toArray();
                ListNode head = constructLinkedList(inputArr);
                int target = Integer.parseInt(line2);
                ListNode resultHeadNode = solution.partition2(head, target);
                StringBuilder sb = new StringBuilder();
                while(resultHeadNode != null){
                   sb.append(resultHeadNode.val).append(",");
                   resultHeadNode = resultHeadNode.next;
                }
                String resultStr = sb.toString().substring(0, sb.lastIndexOf(","));
                String exepctedOutput = outputBr.readLine();
                if(!resultStr.equals(exepctedOutput)){
                    String errorString = String
                            .format("Result doesn't match. Expected = %s, Actual = %s",
                                    exepctedOutput, resultStr);
                    throw new Exception(errorString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ListNode constructLinkedList(int[] inputArr) {
        ListNode head = null;
        ListNode tmp = null;
        for(int num : inputArr){
            ListNode node = new ListNode(num);
            if(head == null) {
                head = node ;
            }
            if(tmp != null){
                tmp.next = node;
            }
            tmp = node;
        }
        return head;
    }

    // Approach #1 : Using queue
    /*
    Explanation - Keeping track of the elements less than x in "lesser" Queue
    and of the elements greater than x in the "higher" Queue. Queue is the
    appropriate Data structure as the ordering has to be guaranteed. Normal array
    would also work instead of queue.
    After traversing all the elements, finally link the lesser elements with the
    higher elements. This will give the expected result.

    Time = O(n) and Space = O(n) since we are maintaining an additional
    data structure to keep a track of the elements
     */
    public ListNode partition(ListNode head, int x) {
        Queue<Integer> lesser = new LinkedList<>();
        Queue<Integer> higher = new LinkedList<>();

        ListNode temp = head;
        while(temp != null){
            if(temp.val < x)   lesser.add(temp.val);
            else if(temp.val >= x)  higher.add(temp.val);
            temp = temp.next;
        }
        temp = head;
        while(!lesser.isEmpty()){
            temp.val = lesser.remove();
            temp = temp.next;
        }
        while(!higher.isEmpty()){
            temp.val = higher.remove();
            temp = temp.next;
        }

        return head;
    }

    // Approach#2 : Space efficient
    /*
    Explanation - Improved version of the above approach where we are not
    including any additional data structure to store elements. Instead, we are
    using additional pointers to rearrange the nodes.
    gt -> will keep track of the last visited node >= x
    lt -> will keep track of the last visited node < x
    gtStart -> will keep track of the beginning of nodes for gt segment
    ltStart -> will keep track of the beginning of nodes for lt segment
    curr -> will keep track of the existing node into consideration

    Finally, link the lt with gtStart to get complete solution.

    Time = O(n) and space = O(1)
     */
    public ListNode partition2(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode curr = head;
        ListNode lt = null;
        ListNode gt = null;
        ListNode ltStart = null;
        ListNode gtStart = null;

        while(curr != null){
            if(curr.val < x){
                if(lt != null){
                    lt.next = curr;
                }
                lt = curr;
                if(ltStart == null){
                    ltStart = lt;
                }
            }else{
                if(gt !=null){
                    gt.next = curr;
                }
                gt = curr;
                if(gtStart == null){
                    gtStart = gt;
                }
            }
            curr = curr.next;
        }
        if(lt !=null){
            lt.next = gtStart;
        }
        if(gt != null){
            gt.next = null;
        }

        head = ltStart != null ? ltStart : gtStart;
        return head;
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


