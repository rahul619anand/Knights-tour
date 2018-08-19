# Knights Tour


### Problem

A pawn can move on 10x10 chequerboard horizontally, vertically and diagonally by these rules:
1. 3 tiles moving North (N), West (W), South (S) and East (E)
2. 2 tiles moving NE, SE, SW and NW
3. Moves are only allowed if the ending tile exists on the board
4. Starting from initial position, the pawn can visit each cell only once

Find at least one path for the pawn to visit all tiles on the board following
the above rules

### Solution 


The problem is solved using H C Warnsdorff's technique which does the following:

1. Start from any tile and mark it as visited.
2. To decide next tile in path, look at all possible unmarked tiles based on moving rules.
3. Rank each possibility by the number of next moves from that tile.
4. Move to any tile with the lowest rank.
5. Add chosen tile to knight's tour path (i.e marked) and repeat the process from last chosen tile.


### Run Solution

sbt run

### Run Test cases 

sbt test




 
