package com.competitive.algo.leetcode._1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();

        try (BufferedReader inputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_1/test-input.txt"));
                BufferedReader outputBr = new BufferedReader(new FileReader("src/main/java/com/competitive/algo/leetcode/_1/test-output.txt"))) {
            String line1;
            while((line1 = inputBr.readLine()) != null){
                String line2 = inputBr.readLine();
                String[] inputs = line1.split(",");
                int[] inputArr = Stream.of(inputs).mapToInt(Integer::parseInt)
                        .toArray();
                int target = Integer.parseInt(line2);
                int[] result = solution.twoSum(inputArr, target);
                String resultStr = String.format("[%d,%d]", result[0], result[1]);
                if(!resultStr.equals(outputBr.readLine())){
                    String errorString = String
                            .format("Result doesn't match. Expected = %s, Actual = %s",
                                    outputBr, resultStr);
                    throw new Exception(errorString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Explanation - Keep storing the details in the map as (value, index).
        Whenever an index is explored from the input array, first check whether
        it's counterpart number, i.e. target - nums[i], exists in the array or not.
        If it exists, then the conditions are met and the requested indices are returned.
        Otherwise, insert into the map (nums[index], index) because future number may
        need a lookup for it's presence.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        for(int index = 0 ; index < nums.length; index++){
            int lookerNum = target - nums[index];
            if(numIndexMap.containsKey(lookerNum)){
                return new int[]{Math.min(numIndexMap.get(lookerNum), index),
                Math.max(numIndexMap.get(lookerNum), index)};
            }
            numIndexMap.put(nums[index], index);
        }
        // Returning empty array but this would never be returned as the
        // problem guarantees that exactly one pair would exist
        return new int[]{};
    }
}
