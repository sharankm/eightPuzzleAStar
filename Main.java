package com.ai;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Sharan's 8 Puzzle solver.");
        System.out.println("\nPlease Enter the Dimension of the puzzle board:");
        int dim = sc.nextInt();
        Integer[][] eightPuzzle = new Integer[dim][dim];
        System.out.println("\nType \"1\" to use a default puzzle, or \"2\" to enter your own puzzle.");
        if (sc.nextInt() == 2) {
            System.out.println("Enter your puzzle, use a zero to represent the blank\n");
            for(int i = 0; i < dim; i++){
                int k = i + 1;
                System.out.println("Enter the " + k +  " row, use space or tabs between numbers  ");
                for(int j=0; j < dim; j++)
                    eightPuzzle[i][j] = sc.nextInt();
            }
        } else {
            eightPuzzle[0][0] = 1;
            eightPuzzle[0][1] = 2;
            eightPuzzle[0][2] = 3;
            eightPuzzle[1][0] = 4;
            eightPuzzle[1][1] = 8;
            eightPuzzle[1][2] = 0;
            eightPuzzle[2][0] = 7;
            eightPuzzle[2][1] = 6;
            eightPuzzle[2][2] = 5;
        }
        Integer[][] solvedPuzzle = new Integer[dim][dim];
        int k = 1;
        for(int i = 0; i < dim; i++)
            for(int j=0; j < dim; j++)
                solvedPuzzle[i][j] = k++;
        solvedPuzzle[dim-1][dim-1] = 0;
        if(!PuzzleUtil.checkValid(eightPuzzle, dim)){
            System.out.print("Please enter Valid input");
            System.exit(0);
        }
        System.out.println("Enter the algorithm\n" +
                "1.\tUniform Cost Search\n" +
                "2.\tA* with the Misplaced Tile heuristic.\n" +
                "3.\tA* with the Manhattan distance heuristic.\n");
        int algo = sc.nextInt();
        ArrayWrapper eightPuzzleWrapper = new ArrayWrapper(eightPuzzle);
        PriorityQueue<ArrayWrapper> queue;
        if (algo == 1) {
            queue = new PriorityQueue<>(10, new UniformComp());
        } else if (algo == 2) {
            queue = new PriorityQueue<>(10, new MisplacedComp());
        } else {
            queue = new PriorityQueue<>(10, new ManhattanComp());
        }
        queue.add(eightPuzzleWrapper);
        int totNodes = 1;
        int maxQSize = 1;
        while(!queue.isEmpty()){
            ArrayWrapper exploreWrapper = queue.poll();
            if (Arrays.deepToString(exploreWrapper.getPuzzle()).equals(Arrays.deepToString(solvedPuzzle))){
                System.out.println("Reached the goal state!!");
                System.out.println("To solve this problem the search algorithm expanded a total of " + totNodes + " nodes.");
                System.out.println("The maximum number of nodes in the queue at any one time was " + maxQSize);
                System.out.println("The depth of the goal node was " + exploreWrapper.getCostVal());
                System.exit(0);
            }
            if(totNodes > 1){
                System.out.println("\nThe best expansion node(s) is:");
                PuzzleUtil.printPuzzle(exploreWrapper.getPuzzle(), dim);
                if (algo == 1) {
                    System.out.println("Uniform cost is: " + exploreWrapper.getCostVal());
                } else if (algo == 2) {
                    System.out.println("Uniform cost is: " + exploreWrapper.getCostVal() + " and Misplaced Tile heuristic is: " + exploreWrapper.getMtVal());
                } else {
                    System.out.println("Uniform cost is: " + exploreWrapper.getCostVal() + " and Manhattan Distance heuristic is: " + exploreWrapper.getMdval());
                }
                if (queue.size() > maxQSize) {
                    maxQSize = queue.size();
                }
            }
            for (int i = 0; i < dim; i++)
                for (int j = 0; j < dim; j++) {
                    if (exploreWrapper.getPuzzle()[i][j] == 0) {
                        if (i > 0) {
                            ArrayWrapper topPuzzle = PuzzleUtil.topPuzzle(exploreWrapper, i, j, dim);
                            queue.add(topPuzzle);
                            totNodes++;
                        }
                        if (j > 0) {
                            ArrayWrapper leftPuzzle = PuzzleUtil.leftPuzzle(exploreWrapper, i, j, dim);
                            queue.add(leftPuzzle);
                            totNodes++;
                        }
                        if (i < dim-1) {
                            ArrayWrapper bottomPuzzle = PuzzleUtil.bottomPuzzle(exploreWrapper, i, j, dim);
                            queue.add(bottomPuzzle);
                            totNodes++;
                        }
                        if (j < dim-1) {
                            ArrayWrapper rightPuzzle = PuzzleUtil.rightPuzzle(exploreWrapper, i, j, dim);
                            queue.add(rightPuzzle);
                            totNodes++;
                        }
                    }
                }
        }
        System.out.println("There is no solution.");
        System.exit(0);
    }
}
