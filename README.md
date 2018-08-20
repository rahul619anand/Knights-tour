# Pawn's Tour


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


### Result

All the points in a discovered (Pawn's tour) path are printed in the console.

Also, matrix representation is shown to analyse moves of the Pawn in the tour according to legal moving rules

#### Sample Output 

    92 	25 	16 	91 	26 	15 	10 	42 	22 	9 	
    66 	75 	94 	65 	38 	95 	64 	39 	96 	63 	
    17 	90 	68 	24 	1 	43 	23 	2 	44 	41 	
    93 	28 	13 	76 	27 	14 	11 	51 	21 	8 	
    67 	74 	4 	32 	37 	3 	31 	40 	97 	62 	
    18 	89 	69 	19 	12 	50 	20 	7 	45 	52 	
    80 	29 	36 	77 	30 	33 	84 	59 	56 	85 	
    70 	73 	5 	49 	72 	6 	46 	53 	98 	61 	
    35 	88 	81 	34 	87 	82 	57 	86 	83 	58 	
    79 	48 	71 	78 	47 	54 	99 	60 	55 	100 

#### Compile 
sbt compile

#### Run Solution
sbt run

#### Run Test cases 
sbt test




 
