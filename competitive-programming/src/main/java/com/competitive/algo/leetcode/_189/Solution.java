package com.competitive.algo.leetcode._189;

public class Solution {

    /*
    Explanation - This approach works on identifying the reshuffled index i.e.
        the correct index where a particular element belongs
        Case 1: when k > N. To neutralize and save extra rotations, consider the
        effective k = k % N
        Case 2: To manage cyclic rotations- A cyclic rotation can only happen
        within 0 <= x < k. When same number is encountered twice, break the inner
        logic of reshuffle and continue with the next index.
     */
    public void rotate(int[] nums, int k) {
        int N = nums.length;

        k = k%N;
        if(N < 2 || k == 0) return;
        int iter = 0;
        for(int i = 0; i < k && iter < N; i++){
            int n = nums[i];
            int j = i;
            while(iter < N){
                int effectiveIndex = (j+k)%N;
                int temp = nums[effectiveIndex];
                nums[effectiveIndex] = n;
                j = effectiveIndex;
                n = temp;
                iter++;
                if(effectiveIndex == i) break;
            }
        }
    }
}


