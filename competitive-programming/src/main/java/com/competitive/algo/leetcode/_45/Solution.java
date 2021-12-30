package com.competitive.algo.leetcode._45;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws Exception {
    Solution solution = new Solution();

        try (BufferedReader inputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_45/test-input.txt"));
                BufferedReader outputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_45/test-output.txt"))) {
            String line1;
            while((line1 = inputBr.readLine()) != null){
                String[] inputs = line1.split(",");
                int[] inputArr = Stream.of(inputs).mapToInt(Integer::parseInt)
                        .toArray();
                int actualResult = solution.jump(inputArr);
                int exepctedOutput = Integer
                        .parseInt(outputBr.readLine());
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

    // To keep track of the minimum jump possible from any specific index
    int[] minJumps;

    public int jump(int[] nums) {
        int len = nums.length;
        minJumps = new int[len];

        Arrays.fill(minJumps, -1);
        // No jump required from the last index, hence making it 0
        minJumps[len-1] = 0;
        for(int i = len-1; i >= 0; i--){
            jump(nums, i);
        }
        return minJumps[0];
    }

    /*
        Explanation - For every index we calculate the minimum jump possible
        to the last element.

        Case 1: If the index at i contains value 0, then no jump possible
        Case 2: If the index at i >= size of remaining elements, then we can
                reach the end of element in just one jump
        Case 3: For all other cases, we would need to check the combinations
                from j = k+i, where 1<=k<= nums[i], and get minimum jump value.
     */
    private void jump(int[] nums, int i){
        if(nums[i] == 0 || i == nums.length-1){
            return;
        }
        if(nums[i] >= nums.length - (i+1)){
            minJumps[i] = 1;
            return;
        }

        int min = Integer.MAX_VALUE;
        for(int k = 1; k <= nums[i] && k < nums.length-1; k++){
            if(minJumps[i+k] != -1)
                min = Math.min(min, minJumps[i+k]);
        }

        minJumps[i] = min == Integer.MAX_VALUE ? -1 : min+1;
    }
}


