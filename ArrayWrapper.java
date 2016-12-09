package com.ai;

public class ArrayWrapper {

    private Integer[][] puzzle;
    private int costVal;
    private int mtVal;
    private int mdval;

    public ArrayWrapper(Integer[][] inputPuzzle){
        puzzle = inputPuzzle;
    }

    public int getCostVal() {
        return costVal;
    }

    public void setCostVal(int costVal) {
        this.costVal = costVal;
    }

    public int getMtVal() {
        return mtVal;
    }

    public void setMtVal(int mtVal) {
        this.mtVal = mtVal;
    }

    public int getMdval() {
        return mdval;
    }

    public void setMdval(int mdval) {
        this.mdval = mdval;
    }

    public Integer[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Integer[][] puzzle) {
        this.puzzle = puzzle;
    }
}
