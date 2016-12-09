package com.ai;

import java.util.ArrayList;
import java.util.List;

public class PuzzleUtil {

    public static boolean checkValid(Integer[][] eightPuzzle, int dim) {
        List<Integer> validMembers = new ArrayList<>();
        for (int i = 0; i < dim * dim; i++) {
            validMembers.add(i);
        }
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (validMembers.contains(eightPuzzle[i][j])) {
                    validMembers.remove(eightPuzzle[i][j]);
                } else {
                    return false;
                }
            }
        return true;
    }

    public static void printPuzzle(Integer[][] eightPuzzle, int dim) {
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                System.out.print(eightPuzzle[i][j] + "\t");
                if (j == 2)
                    System.out.print("\n");
            }
    }

    public static ArrayWrapper topPuzzle(ArrayWrapper puzzleBoard, int x, int y, int dim) {
        Integer[][] PuzzleArray = puzzleBoard.getPuzzle();
        Integer[][] newPuzzleArray = new Integer[dim][dim];
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (i == x && j == y) {
                    newPuzzleArray[i][j] = PuzzleArray[i - 1][j];
                } else if (i == x - 1 && j == y) {
                    newPuzzleArray[i][j] = 0;
                } else {
                    newPuzzleArray[i][j] = PuzzleArray[i][j];
                }
            }

        ArrayWrapper newPuzzleBoard = new ArrayWrapper(newPuzzleArray);
        newPuzzleBoard.setCostVal(puzzleBoard.getCostVal() + 1);
        newPuzzleBoard.setMdval(getMDHeuristic(newPuzzleBoard, dim));
        newPuzzleBoard.setMtVal(getMTHeuristic(newPuzzleBoard, dim));
        return newPuzzleBoard;
    }

    public static ArrayWrapper bottomPuzzle(ArrayWrapper puzzleBoard, int x, int y, int dim) {
        Integer[][] PuzzleArray = puzzleBoard.getPuzzle();
        Integer[][] newPuzzleArray = new Integer[dim][dim];
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (i == x && j == y) {
                    newPuzzleArray[i][j] = PuzzleArray[i + 1][j];
                } else if (i == x + 1 && j == y) {
                    newPuzzleArray[i][j] = 0;
                } else {
                    newPuzzleArray[i][j] = PuzzleArray[i][j];
                }
            }
        ArrayWrapper newPuzzleBoard = new ArrayWrapper(newPuzzleArray);
        newPuzzleBoard.setCostVal(puzzleBoard.getCostVal() + 1);
        newPuzzleBoard.setMdval(getMDHeuristic(newPuzzleBoard, dim));
        newPuzzleBoard.setMtVal(getMTHeuristic(newPuzzleBoard, dim));
        return newPuzzleBoard;
    }

    public static ArrayWrapper leftPuzzle(ArrayWrapper puzzleBoard, int x, int y, int dim) {
        Integer[][] PuzzleArray = puzzleBoard.getPuzzle();
        Integer[][] newPuzzleArray = new Integer[dim][dim];
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (i == x && j == y) {
                    newPuzzleArray[i][j] = PuzzleArray[i][j - 1];
                } else if (i == x && j == y - 1) {
                    newPuzzleArray[i][j] = 0;
                } else {
                    newPuzzleArray[i][j] = PuzzleArray[i][j];
                }
            }
        ArrayWrapper newPuzzleBoard = new ArrayWrapper(newPuzzleArray);
        newPuzzleBoard.setCostVal(puzzleBoard.getCostVal() + 1);
        newPuzzleBoard.setMdval(getMDHeuristic(newPuzzleBoard, dim));
        newPuzzleBoard.setMtVal(getMTHeuristic(newPuzzleBoard, dim));
        return newPuzzleBoard;
    }

    public static ArrayWrapper rightPuzzle(ArrayWrapper puzzleBoard, int x, int y, int dim) {
        Integer[][] PuzzleArray = puzzleBoard.getPuzzle();
        Integer[][] newPuzzleArray = new Integer[dim][dim];
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (i == x && j == y) {
                    newPuzzleArray[i][j] = PuzzleArray[i][j + 1];
                } else if (i == x && j == y + 1) {
                    newPuzzleArray[i][j] = 0;
                } else {
                    newPuzzleArray[i][j] = PuzzleArray[i][j];
                }
            }
        ArrayWrapper newPuzzleBoard = new ArrayWrapper(newPuzzleArray);
        newPuzzleBoard.setCostVal(puzzleBoard.getCostVal() + 1);
        newPuzzleBoard.setMdval(getMDHeuristic(newPuzzleBoard, dim));
        newPuzzleBoard.setMtVal(getMTHeuristic(newPuzzleBoard, dim));
        return newPuzzleBoard;
    }

    public static int getMDHeuristic(ArrayWrapper puzzleBoard, int dim) {
        Integer[][] puzzleArray = puzzleBoard.getPuzzle();
        Integer[][] goalArray = new Integer[dim][dim];
        int heuristic = 0;
        int k = 1;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                goalArray[i][j] = k++;
            }
        goalArray[dim-1][dim-1] = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (!puzzleArray[i][j].equals(goalArray[i][j])) {
                    for (int l = 0; l < dim; l++)
                        for (int m = 0; m < dim; m++) {
                            if (puzzleArray[i][j].equals(goalArray[l][m])) {
                                heuristic = heuristic + Math.abs(i - l) + Math.abs(j - m);
                            }
                        }
                }
            }
        return heuristic;
    }

    public static int getMTHeuristic(ArrayWrapper puzzleBoard, int dim) {
        Integer[][] puzzleArray = puzzleBoard.getPuzzle();
        Integer[][] goalArray = new Integer[dim][dim];
        int misplaced = 0;
        int k = 1;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                goalArray[i][j] = k++;
            }
        goalArray[dim-1][dim-1] = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (!puzzleArray[i][j].equals(goalArray[i][j])) {
                    misplaced++;
                }
            }
        return misplaced - 1;
    }
}
