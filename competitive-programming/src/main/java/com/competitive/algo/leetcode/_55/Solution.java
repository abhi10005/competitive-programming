package com.competitive.algo.leetcode._55;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws Exception {
    Solution solution = new Solution();

        try (BufferedReader inputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_55/test-input.txt"));
                BufferedReader outputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_55/test-output.txt"))) {
            String line1;
            while((line1 = inputBr.readLine()) != null){
                String[] inputs = line1.split(",");
                int[] inputArr = Stream.of(inputs).mapToInt(Integer::parseInt)
                        .toArray();
                boolean actualResult = solution.canJump(inputArr);
                boolean exepctedOutput = Boolean
                        .parseBoolean(outputBr.readLine());
                if(actualResult != exepctedOutput){
                    String errorString = String
                            .format("Result doesn't match. Expected = %s, Actual = %s for input %s",
                                    exepctedOutput, actualResult,
                                    Arrays.toString(inputArr));
                    throw new Exception(errorString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // To keep track of all the visited indices in case it's impossible to reach
    // the last element of the list from the respective index
    boolean[] tracker;

    public boolean canJump(int[] nums) {
        tracker = new boolean[nums.length];
        Arrays.fill(tracker, true);
        return canJump(nums, 0);
    }

    /*
    Explanation - This is a recursive solution. Without tracker[], the solution
    would grow to be exponential time complexity. With tracker[], the time
    complexity is reduced to O(n^2).

    Case 1: if the element at index i has value greater or equal to the
    length of array e.g arr = [3, 1], then for i = 0, it is always possible to
    reach the end of the array

    Case 2: if element at index i is 0, then it's impossible to reach the end
    Case 3: if tracker at index i is false, then it's impossible to reach the end
    Case 4: For all other cases, we would need to check the combinations
    from j = k+i, where 1<=k<= nums[i], such that tracker[j] != false

    Note: tracker[i] == false, indicates with confidence that it's impossible to
    reach the end of the array from index i. This works same as bloom filter
     */
    private boolean canJump(int[]nums, int i){
        if(i == nums.length-1) return true;
        if(nums[i] == 0 || !tracker[i]) return false;
        if(nums[i] >= nums.length - (i+1)) return true;

        for(int k = 1; k <= nums[i]; k++){
            boolean res = canJump(nums, i+k);
            if(res)
                return true;
            tracker[i+k] = false;
        }
        return false;
    }
}


