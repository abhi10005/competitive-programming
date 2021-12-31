package com.competitive.algo.leetcode._200;

import java.util.Stack;

public class Solution {

    class Coordinate{
        int x; int y;

        Coordinate(int x, int y){
            this. x = x;
            this.y = y;
        }
    }

    /*
    Explanation - The below solution is implemented using BFS(Breadth First Search)
    approach. DFS(Depth First Search) also is an alternative.
    The idea is to keep a track of all the coordinates that are visited by marking
    them as 'X' and thereby increasing the count on first encounter where
    coordinate (x,y) has value '1'.. Then, we push the qualified horizontal and
    vertical adjacent cells/coordinates into stack and repeat the process until
    empty.
     */
    public int numIslands(char[][] grid) {
        Stack<Coordinate> connected = new Stack<>();
        int m = grid.length;
        int n = grid[0].length;

        int islands = 0;

        for(int i =0; i < m; i++){
            for (int j =0 ; j < n; j++){
                if(grid[i][j] == '1'){
                    islands++;
                    connected.push(new Coordinate(i, j));
                    while(!connected.isEmpty()){
                        Coordinate cdt = connected.pop();
                        // Check boundary conditions
                        if(cdt.x != m-1 && grid[(cdt.x)+1][cdt.y] == '1'){
                            connected.push(new Coordinate((cdt.x) + 1, cdt.y));
                        }
                        if(cdt.y != n-1 && grid[cdt.x][(cdt.y)+1] == '1'){
                            connected.push(new Coordinate(cdt.x, (cdt.y)+1));
                        }
                        if(cdt.x != 0 && grid[(cdt.x)-1][cdt.y] == '1'){
                            connected.push(new Coordinate((cdt.x) - 1, cdt.y));
                        }
                        if(cdt.y != 0 && grid[cdt.x][(cdt.y)-1] == '1'){
                            connected.push(new Coordinate(cdt.x, (cdt.y)-1));
                        }
                        grid[cdt.x][cdt.y] = 'X';
                    }
                }
            }
        }
        return islands;
    }
}


